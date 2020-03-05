package br.com.unip.autenticacao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class CadastroDTO(@JsonProperty(value = "uuid") val uuid: String,
                  @JsonProperty(value = "status") val status: String)