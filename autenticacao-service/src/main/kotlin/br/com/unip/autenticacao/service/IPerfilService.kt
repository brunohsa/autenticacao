package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.repository.entity.Perfil

interface IPerfilService {

    fun buscarPorNome(nome : String) : Perfil?

}