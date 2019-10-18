package br.com.unip.cardapio.service

import br.com.unip.cardapio.dto.PermissaoDTO
import br.com.unip.cardapio.dto.UsuarioDTO

interface IUsuarioService {

    fun cadastrarConsumidor(usuario : UsuarioDTO)

    fun cadastrarFornecedor(usuario: UsuarioDTO)

    fun buscarPermissoes(email: String): List<PermissaoDTO>

    fun usuarioCadastrado(email : String) : Boolean

    fun cadastrarUsuarioOAuth(usuario: UsuarioDTO)
}