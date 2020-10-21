package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.domain.LoginFirebaseDomain
import br.com.unip.autenticacao.domain.UsuarioDomain
import br.com.unip.autenticacao.dto.firebase.FirebaseEmailSenhaDTO
import br.com.unip.autenticacao.dto.firebase.OAuthLoginDTO
import br.com.unip.autenticacao.dto.firebase.UsuarioAutenticadoDTO
import br.com.unip.autenticacao.service.IRestService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class FirebaseRepository(val restService: IRestService) : IFirebaseRepository {

    @Value("\${firebase.api.key}")
    private val keyAPI = ""

    @Value("\${firebase.email.sing-up.url}")
    private val urlCadastro = ""

    @Value("\${firebase.email.sing-in.url}")
    private val urlLogin = ""

    @Value("\${firebase.oauth.sing-in.url}")
    private val facebookLogin = ""

    @Value("\${firebase.request.uri}")
    private val requestUri: String = ""

    override fun autenticar(domain: LoginFirebaseDomain): UsuarioAutenticadoDTO {
        val request = FirebaseEmailSenhaDTO(domain.email.get(), domain.senha.get())
        return restService.post(urlLogin + keyAPI, request, UsuarioAutenticadoDTO::class)
    }

    override fun autenticarOAuth(token: String, provider: String): UsuarioAutenticadoDTO {
        val postBody = "access_token=$token&providerId=$provider"
        val request = OAuthLoginDTO(postBody, requestUri)
        return restService.post(facebookLogin + keyAPI, request, UsuarioAutenticadoDTO::class)
    }

    override fun cadastrarUsuario(domain: UsuarioDomain) {
        val request = FirebaseEmailSenhaDTO(domain.email.get(), domain.senha.get())
        restService.post(urlCadastro + keyAPI, request)
    }
}