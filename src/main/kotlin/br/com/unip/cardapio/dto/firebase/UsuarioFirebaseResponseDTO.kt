package br.com.unip.cardapio.dto.firebase

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class UsuarioFirebaseResponseDTO(@JsonProperty("localId") val localId: String,
                                 @JsonProperty("email") val email: String)