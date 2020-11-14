package br.com.unip.autenticacao.util

import br.com.unip.autenticacao.dto.DadosAutenticacaoDTO
import br.com.unip.autenticacao.dto.firebase.UsuarioAutenticadoDTO
import br.com.unip.autenticacao.service.IUsuarioService
import com.google.firebase.auth.FirebaseAuth
import org.springframework.stereotype.Component
import java.util.HashMap

@Component
class GeradorTokenUtil(val usuarioService: IUsuarioService) {

    fun gerar(usuarioAutenticado: UsuarioAutenticadoDTO): DadosAutenticacaoDTO {
        val usuario = usuarioService.buscar(usuarioAutenticado.email)

        val additionalClaims = HashMap<String, Any?>()
        additionalClaims["scopes"] = this.buscarPremissoes(usuario.email!!)
        additionalClaims["email"] = usuario.email
        additionalClaims["cadastro_uuid"] = usuario.cadastroUUID
        additionalClaims["usuario_uuid"] = usuario.uuid

        val token = FirebaseAuth.getInstance().createCustomToken(usuarioAutenticado.localId, additionalClaims)
        return DadosAutenticacaoDTO(usuario.email, usuario.cadastroUUID, token)
    }

    private fun buscarPremissoes(email: String): Set<String> {
        return usuarioService.buscarPermissoes(email).map { permissao -> permissao.nome }.toSet()
    }
}