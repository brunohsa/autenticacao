package br.com.unip.cardapio.dto.firebase

import com.fasterxml.jackson.annotation.JsonProperty

class FirebaseEmailSenhaDTO(@JsonProperty("email") val email: String,
                            @JsonProperty("password") val senha: String,
                            @JsonProperty("returnSecureToken") returnSecureToken: Boolean = true)