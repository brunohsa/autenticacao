package br.com.unip.autenticacao.webservice.model.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class VinculoUsuarioRequest(@JsonProperty("uuid_pessoa") val uuidPessoa: String)