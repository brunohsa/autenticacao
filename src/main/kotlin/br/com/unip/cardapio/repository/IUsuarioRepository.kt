package br.com.unip.cardapio.repository

import br.com.unip.cardapio.UsuarioDomain
import br.com.unip.cardapio.dto.PermissaoDTO
import br.com.unip.cardapio.dto.UsuarioDTO

interface IUsuarioRepository {

    fun buscar(username: String): UsuarioDTO?

    fun buscarPermissoes(id: Long): List<PermissaoDTO>

    fun criar(domain : UsuarioDomain)

}