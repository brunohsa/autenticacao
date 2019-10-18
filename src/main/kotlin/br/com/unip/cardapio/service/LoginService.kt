package br.com.unip.cardapio.service

import br.com.unip.cardapio.domain.LoginFirebaseDomain
import br.com.unip.cardapio.domain.OAuthLoginDomain
import br.com.unip.cardapio.dto.UsuarioDTO
import br.com.unip.cardapio.dto.firebase.LoginDTO
import br.com.unip.cardapio.dto.firebase.OAuthLoginFirebaseDTO
import br.com.unip.cardapio.repository.IFirebaseRepository
import br.com.unip.cardapio.util.TokenUtil
import org.springframework.stereotype.Service

@Service
class LoginService(val firebaseRepository: IFirebaseRepository, val tokenUtil: TokenUtil,
                   val usuarioService: IUsuarioService) : ILoginService {

    override fun autenticar(dto: LoginDTO): String {
        val domain = LoginFirebaseDomain(dto.email, dto.senha)
        val usuarioAutenticado = firebaseRepository.autenticar(domain)
        return tokenUtil.gerarToken(usuarioAutenticado)
    }

    override fun autenticarViaOAuth(dto: OAuthLoginFirebaseDTO): String {
        val domain = OAuthLoginDomain(dto.tokenDeAcesso, dto.providerId, dto.requestUri)
        val usuarioAutenticado = firebaseRepository.autenticarViaOAuth(domain)

        if (!usuarioService.usuarioCadastrado(usuarioAutenticado.email)) {
            var dto = UsuarioDTO(usuarioAutenticado.email)
            usuarioService.cadastrarUsuarioOAuth(dto)
        }
        return tokenUtil.gerarToken(usuarioAutenticado)
    }
}