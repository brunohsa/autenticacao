package br.com.unip.cardapio.repository

import br.com.unip.cardapio.repository.entity.Perfil

interface IPerfilRepository {

    fun buscarPorNome(nome : String) : Perfil?

    fun buscarPorNomes(nomes: List<String>): List<Perfil>
}