package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.configuration.FirebaseProvider.FACEBOOK_PROVIDER
import br.com.unip.autenticacao.domain.LoginFirebaseDomain
import br.com.unip.autenticacao.dto.PessoaFisicaDTO
import br.com.unip.autenticacao.dto.UsuarioDTO
import br.com.unip.autenticacao.dto.firebase.LoginDTO
import br.com.unip.autenticacao.dto.firebase.UsuarioAutenticadoDTO
import br.com.unip.autenticacao.repository.IFirebaseRepository
import br.com.unip.autenticacao.util.GeradorTokenUtil
import org.springframework.stereotype.Service

@Service
class LoginService(val firebaseRepository: IFirebaseRepository,
                   val tokenUtil: GeradorTokenUtil,
                   val usuarioService: IUsuarioService) : ILoginService {

    override fun autenticar(dto: LoginDTO): String {
        val domain = LoginFirebaseDomain(dto.email, dto.senha)
        val usuarioAutenticado = firebaseRepository.autenticar(domain)
        return tokenUtil.gerar(usuarioAutenticado)
    }

    override fun autenticarOAuth(apikey: String): String {
        val usuario = usuarioService.buscarPorApiKey(apikey)
        val login = LoginDTO(usuario.email!!, usuario.senha!!)
        return autenticar(login)
    }

    override fun autenticarViaFacebook(token: String): String {
        val usuarioAutenticado = firebaseRepository.autenticarOAuth(token, FACEBOOK_PROVIDER)
        this.cadastrarCasoNaoPossuaCadastro(usuarioAutenticado)

        return tokenUtil.gerar(usuarioAutenticado)
    }

    private fun cadastrarCasoNaoPossuaCadastro(usuario: UsuarioAutenticadoDTO) {
        if (usuarioService.usuarioCadastrado(usuario.email)) {
            return
        }
        val pf = PessoaFisicaDTO(usuario.nome, usuario.sobrenome)
        val usuarioDTO = UsuarioDTO(usuario.email, pf)
        usuarioService.cadastrarUsuarioOAuth(usuarioDTO)
    }
}