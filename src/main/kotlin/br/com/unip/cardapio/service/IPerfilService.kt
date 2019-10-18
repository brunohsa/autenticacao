package br.com.unip.cardapio.service

import br.com.unip.cardapio.repository.entity.Perfil

interface IPerfilService {

    fun buscarPorNome(nome : String) : Perfil?

}