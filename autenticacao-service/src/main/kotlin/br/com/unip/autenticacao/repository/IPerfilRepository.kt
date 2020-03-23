package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.repository.entity.Perfil

interface IPerfilRepository {

    fun buscarPorNome(nome : String) : Perfil?

    fun buscarPorNomes(nomes: List<String>): List<Perfil>
}