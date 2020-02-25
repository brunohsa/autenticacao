package br.com.unip.autenticacaolib.util

import br.com.unip.autenticacaolib.AuthenticationToken
import br.com.unip.autenticacaolib.dto.DadosUsuarioLogado
import org.springframework.security.core.context.SecurityContextHolder

class AutenthicationUtil {

    companion object {
        fun getDadosUsuarioLogado(): DadosUsuarioLogado {
            val dados = SecurityContextHolder.getContext().authentication as AuthenticationToken
            return DadosUsuarioLogado(dados.email, dados.cadastroUUID, dados.usuarioUUID)
        }
    }
}