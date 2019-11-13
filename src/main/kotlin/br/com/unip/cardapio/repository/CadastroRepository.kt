package br.com.unip.cardapio.repository

import br.com.unip.cardapio.dto.CadastroDTO
import br.com.unip.cardapio.service.IRestService
import org.springframework.stereotype.Repository

@Repository
class CadastroRepository(val restService: IRestService) : ICadastroRepository {

    override fun buscar(uuid: String): CadastroDTO {
        return restService.get("http://localhost:8082/cadastro/api/v1/cadastros/$uuid", CadastroDTO::class)
    }
}