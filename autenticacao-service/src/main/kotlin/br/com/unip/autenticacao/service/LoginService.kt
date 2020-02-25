package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.domain.LoginFirebaseDomain
import br.com.unip.autenticacao.domain.OAuthLoginDomain
import br.com.unip.autenticacao.dto.UsuarioDTO
import br.com.unip.autenticacao.dto.firebase.LoginDTO
import br.com.unip.autenticacao.dto.firebase.OAuthLoginFirebaseDTO
import br.com.unip.autenticacao.repository.IFirebaseRepository
import br.com.unip.autenticacao.util.GeradorTokenUtil
import org.springframework.stereotype.Service

@Service
class LoginService(val firebaseRepository: IFirebaseRepository, val tokenUtil: GeradorTokenUtil,
                   val usuarioService: IUsuarioService) : ILoginService {

    override fun autenticar(dto: LoginDTO): String {
        val domain = LoginFirebaseDomain(dto.email, dto.senha)
        val usuarioAutenticado = firebaseRepository.autenticar(domain)
        return tokenUtil.gerar(usuarioAutenticado)
    }

    override fun autenticarViaOAuth(dto: OAuthLoginFirebaseDTO): String {
        val domain = OAuthLoginDomain(dto.tokenDeAcesso, dto.providerId, dto.requestUri)
        val usuarioAutenticado = firebaseRepository.autenticarViaOAuth(domain)

        if (!usuarioService.usuarioCadastrado(usuarioAutenticado.email)) {
            var usuarioDTO = UsuarioDTO(usuarioAutenticado.email)
            usuarioService.cadastrarUsuarioOAuth(usuarioDTO)
        }
        return tokenUtil.gerar(usuarioAutenticado)
    }
}