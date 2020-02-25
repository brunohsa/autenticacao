package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.dto.CadastroDTO
import br.com.unip.autenticacao.dto.PessoaFisicaDTO
import br.com.unip.autenticacao.dto.PessoaJuridicaDTO

interface ICadastroRepository {

    fun cadastrar(domain : PessoaFisicaDTO) : String

    fun cadastrar(domain : PessoaJuridicaDTO) : String

    fun buscar(uuid: String): CadastroDTO
}