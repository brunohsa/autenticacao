package br.com.unip.cardapio.service

import br.com.unip.cardapio.dto.UsuarioDTO

interface IUsuarioService {

    fun criarUsuario(usuario : UsuarioDTO)
}