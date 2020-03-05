package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.dto.CadastroDTO
import br.com.unip.autenticacao.dto.PessoaFisicaDTO
import br.com.unip.autenticacao.dto.PessoaJuridicaDTO
import br.com.unip.autenticacao.service.IRestService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.util.LinkedMultiValueMap

@Repository
class CadastroRepository(val restService: IRestService) : ICadastroRepository {

    @Value("\${apikey.autenticacao}")
    private val APIKEY_AUTENTICACAO = ""

    @Value("\${microservice.cadastro.url}")
    private val CADASTRO_URL = ""

    private val APIKEY_TAG = "key"

    private val PESSOA_FISICA_URL = "v1/pessoa-fisica"
    private val PESSOA_JURIDICA_URL = "v1/pessoa-juridica"
    private val CADASTROS_URL = "v1/cadastros"

    override fun cadastrar(dto: PessoaFisicaDTO): String {
        val headers = getHeaderComApiKey()
        return restService.post("$CADASTRO_URL$PESSOA_FISICA_URL/cadastrar", dto, headers)
    }

    override fun cadastrar(dto: PessoaJuridicaDTO): String {
        val headers = getHeaderComApiKey()
        return restService.post("$CADASTRO_URL$PESSOA_JURIDICA_URL/cadastrar", dto, headers)
    }

    override fun buscar(uuid: String): CadastroDTO {
        val headers = getHeaderComApiKey()
        return restService.get("$CADASTRO_URL$CADASTROS_URL/$uuid", CadastroDTO::class, headers)
    }

    private fun getHeaderComApiKey(): LinkedMultiValueMap<String, String> {
        val headers = LinkedMultiValueMap<String, String>()
        headers.add(APIKEY_TAG, APIKEY_AUTENTICACAO)

        return headers
    }
}