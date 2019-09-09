package br.com.unip.cardapio.webservice.model.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class UsuarioRequest(@JsonProperty("usuario") val username: String,
                     @JsonProperty("senha") val password: String,
                     @JsonProperty("identificador_pessoa") val uuidPessoa: String)