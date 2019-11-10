package br.com.unip.cardapio.repository

import br.com.unip.cardapio.domain.UsuarioDomain
import br.com.unip.cardapio.dto.PermissaoDTO
import br.com.unip.cardapio.dto.UsuarioDTO

interface IUsuarioRepository {

    fun buscar(username: String): UsuarioDTO?

    fun buscarPermissoes(id: Long): List<PermissaoDTO>

    fun buscarPermissoes(email: String): List<PermissaoDTO>

    fun criar(domain : UsuarioDomain, cadastroUuid : String)

    fun criar(domain : UsuarioDomain)

    fun usuarioCadastrado(email : String) : Boolean

}