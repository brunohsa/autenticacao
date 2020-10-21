package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.dto.PessoaFisicaDTO
import br.com.unip.autenticacao.dto.PessoaJuridicaDTO

interface ICadastroService {

    fun cadastrar(dto : PessoaFisicaDTO) : String

    fun cadastrar(dto : PessoaJuridicaDTO) : String
}