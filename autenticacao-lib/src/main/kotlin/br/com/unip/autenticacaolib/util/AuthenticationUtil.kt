package br.com.unip.autenticacaolib.util

import br.com.unip.autenticacaolib.AuthenticationToken
import br.com.unip.autenticacaolib.dto.DadosUsuarioLogado
import org.springframework.security.core.context.SecurityContextHolder

class AuthenticationUtil {

    companion object {

        fun getDadosUsuarioLogado(): DadosUsuarioLogado {
            val dados = getAuthenticationToken()
            return DadosUsuarioLogado(dados.email, dados.cadastroUUID, dados.usuarioUUID)
        }

        fun getEmail(): String {
            return getAuthenticationToken().email
        }

        fun getCadastroUUID(): String? {
            return getAuthenticationToken().cadastroUUID
        }

        private fun getAuthenticationToken(): AuthenticationToken {
            return SecurityContextHolder.getContext().authentication as AuthenticationToken
        }
    }
}