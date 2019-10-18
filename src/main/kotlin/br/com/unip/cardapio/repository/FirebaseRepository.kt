package br.com.unip.cardapio.repository

import br.com.unip.cardapio.domain.LoginFirebaseDomain
import br.com.unip.cardapio.domain.OAuthLoginDomain
import br.com.unip.cardapio.domain.UsuarioDomain
import br.com.unip.cardapio.dto.firebase.FirebaseEmailSenhaDTO
import br.com.unip.cardapio.dto.firebase.OAuthLoginDTO
import br.com.unip.cardapio.dto.firebase.UsuarioAutenticadoDTO
import br.com.unip.cardapio.dto.firebase.UsuarioFirebaseResponseDTO
import br.com.unip.cardapio.service.IRestService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class FirebaseRepository(val restService: IRestService) : IFirebaseRepository {

    @Value("\${firebase.api.key}")
    val keyAPI = ""

    @Value("\${firebase.email.sing-up.url}")
    val urlCadastro = ""

    @Value("\${firebase.email.sing-in.url}")
    val urlLogin = ""

    @Value("\${firebase.oauth.sing-in.url}")
    val facebookLogin = ""

    override fun autenticar(domain: LoginFirebaseDomain): UsuarioAutenticadoDTO {
        val request = FirebaseEmailSenhaDTO(domain.email.get(), domain.senha.get())
        return restService.post(urlLogin + keyAPI, request, UsuarioAutenticadoDTO::class)
    }

    override fun autenticarViaOAuth(domain: OAuthLoginDomain): UsuarioAutenticadoDTO {
        val postBody = "access_token=${domain.tokenDeAcesso.campo}&providerId=${domain.providerId.campo}"
        val request = OAuthLoginDTO(postBody, domain.requestUri.campo!!)
        return restService.post(facebookLogin + keyAPI, request, UsuarioAutenticadoDTO::class)
    }

    override fun cadastrarUsuario(domain: UsuarioDomain): UsuarioFirebaseResponseDTO {
        val request = FirebaseEmailSenhaDTO(domain.email.get(), domain.senha.get())
        return restService.post(urlCadastro + keyAPI, request, UsuarioFirebaseResponseDTO::class)
    }
}