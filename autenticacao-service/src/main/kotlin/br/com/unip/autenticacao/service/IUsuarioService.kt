package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.dto.PermissaoDTO
import br.com.unip.autenticacao.dto.UsuarioDTO

interface IUsuarioService {

    fun cadastrarConsumidor(usuario: UsuarioDTO)

    fun cadastrarFornecedor(usuario: UsuarioDTO)

    fun buscarPermissoes(email: String): List<PermissaoDTO>

    fun usuarioCadastrado(email: String): Boolean

    fun buscar(email: String) : UsuarioDTO

    fun buscarPorApiKey(apiKey: String) : UsuarioDTO
}