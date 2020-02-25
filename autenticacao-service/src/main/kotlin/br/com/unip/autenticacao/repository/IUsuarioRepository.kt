package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.domain.UsuarioDomain
import br.com.unip.autenticacao.dto.PermissaoDTO
import br.com.unip.autenticacao.dto.UsuarioDTO

interface IUsuarioRepository {

    fun buscar(username: String): UsuarioDTO?

    fun buscarPermissoes(id: Long): List<PermissaoDTO>

    fun buscarPermissoes(email: String): List<PermissaoDTO>

    fun criar(domain : UsuarioDomain, cadastroUuid : String)

    fun criar(domain : UsuarioDomain)

    fun usuarioCadastrado(email : String) : Boolean

    fun buscarPorEmail(email: String): UsuarioDTO
}