package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.dto.PessoaFisicaDTO
import br.com.unip.autenticacao.dto.PessoaJuridicaDTO

interface ICadastroRepository {

    fun cadastrar(dto : PessoaFisicaDTO) : String

    fun cadastrar(dto : PessoaJuridicaDTO) : String
}