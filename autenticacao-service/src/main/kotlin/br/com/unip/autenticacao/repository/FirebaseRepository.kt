package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.domain.LoginFirebaseDomain
import br.com.unip.autenticacao.domain.UsuarioDomain
import br.com.unip.autenticacao.dto.firebase.FirebaseEmailSenhaDTO
import br.com.unip.autenticacao.dto.firebase.UsuarioAutenticadoDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class FirebaseRepository(val restRepository: IRestRepository) : IFirebaseRepository {

    @Value("\${firebase.api.key}")
    private val keyAPI = ""

    @Value("\${firebase.email.sing-up.url}")
    private val urlCadastro = ""

    @Value("\${firebase.email.sing-in.url}")
    private val urlLogin = ""

    override fun autenticar(domain: LoginFirebaseDomain): UsuarioAutenticadoDTO {
        val request = FirebaseEmailSenhaDTO(domain.email.get(), domain.senha.get())
        return restRepository.post(urlLogin + keyAPI, request, UsuarioAutenticadoDTO::class)
    }

    override fun cadastrarUsuario(domain: UsuarioDomain) {
        val request = FirebaseEmailSenhaDTO(domain.email.get(), domain.senha.get())
        restRepository.post(urlCadastro + keyAPI, request)
    }
}