package br.com.unip.cardapio.repository

import br.com.unip.cardapio.dto.CadastroDTO

interface ICadastroRepository {

    fun buscar(uuid: String): CadastroDTO
}