package br.com.unip.cardapio.repository

import br.com.unip.cardapio.dto.PessoaFisicaDTO
import br.com.unip.cardapio.dto.PessoaJuridicaDTO
import br.com.unip.cardapio.service.IRestService
import org.springframework.stereotype.Repository

@Repository
class PessoaRepository(val restService: IRestService) : IPessoaRepository {

    override fun cadastrar(dto: PessoaFisicaDTO): String {
        return restService.post("http://localhost:8082/cadastro/api/v1/cadastros/pessoa-fisica", dto)
    }

    override fun cadastrar(dto: PessoaJuridicaDTO): String {
        return restService.post("http://localhost:8082/cadastro/api/v1/cadastros/pessoa-juridica", dto)
    }
}