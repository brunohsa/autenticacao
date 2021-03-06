package br.com.unip.autenticacao.dto.firebase

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class UsuarioAutenticadoDTO {

    @JsonProperty("localId")
    lateinit var localId: String

    @JsonProperty("email")
    lateinit var email: String

    @JsonProperty("firstName")
    var nome: String? = ""

    @JsonProperty("lastName")
    var sobrenome: String? = ""
}