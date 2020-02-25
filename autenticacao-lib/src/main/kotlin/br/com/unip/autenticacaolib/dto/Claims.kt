package br.com.unip.autenticacaolib.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Claims {

    @JsonProperty(value = "scopes")
    var scopes: List<String> = emptyList()

    @JsonProperty(value = "email")
    var email: String? = ""

    @JsonProperty(value = "cadastro_uuid")
    var cadastroUUID: String? = ""

    @JsonProperty(value = "usuario_uuid")
    var usuarioUUID: String? = ""
}