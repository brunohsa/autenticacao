package br.com.unip.cardapio.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class CadastroResponse(@JsonProperty(value = "uuid") val uuid: String,
                       @JsonProperty(value = "status") val status: String,
                       @JsonProperty(value = "pessoa") val pessoa: PessoaResponse)