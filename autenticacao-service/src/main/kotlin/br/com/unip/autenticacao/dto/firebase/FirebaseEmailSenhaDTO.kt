package br.com.unip.autenticacao.dto.firebase

import com.fasterxml.jackson.annotation.JsonProperty

class FirebaseEmailSenhaDTO(@JsonProperty("email") val email: String,
                            @JsonProperty("password") val senha: String,
                            @JsonProperty("returnSecureToken") val returnSecureToken: Boolean = true)