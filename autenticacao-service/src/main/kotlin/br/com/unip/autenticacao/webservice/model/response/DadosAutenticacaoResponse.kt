package br.com.unip.autenticacao.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class DadosAutenticacaoResponse(@JsonProperty(value = "email") val email: String,
                                @JsonProperty(value = "cadastro_uuid") var cadastroUUID: String?)