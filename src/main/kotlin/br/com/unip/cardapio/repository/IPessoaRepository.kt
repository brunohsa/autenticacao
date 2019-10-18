package br.com.unip.cardapio.repository

import br.com.unip.cardapio.dto.PessoaFisicaDTO
import br.com.unip.cardapio.dto.PessoaJuridicaDTO

interface IPessoaRepository {

    fun cadastrar(domain : PessoaFisicaDTO) : String

    fun cadastrar(domain : PessoaJuridicaDTO) : String
}