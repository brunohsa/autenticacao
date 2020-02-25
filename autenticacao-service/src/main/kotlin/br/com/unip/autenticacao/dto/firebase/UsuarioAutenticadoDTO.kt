package br.com.unip.autenticacao.dto.firebase

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class UsuarioAutenticadoDTO(@JsonProperty("localId") val localId: String,
                            @JsonProperty("email") val email: String)