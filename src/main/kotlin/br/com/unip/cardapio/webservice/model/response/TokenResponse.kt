package br.com.unip.cardapio.webservice.model.response

import br.com.unip.cardapio.dto.PessoaDTO
import br.com.unip.cardapio.exception.ECodigoErro
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class TokenResponse(@JsonProperty(value = "token") val token: String)