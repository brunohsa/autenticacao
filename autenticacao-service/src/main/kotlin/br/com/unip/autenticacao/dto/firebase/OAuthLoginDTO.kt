package br.com.unip.autenticacao.dto.firebase

import com.fasterxml.jackson.annotation.JsonProperty

class OAuthLoginDTO(
        @JsonProperty("postBody") val postBody: String,
        @JsonProperty("requestUri") val requestUri: String,
        @JsonProperty("returnIdpCredential") val returnIdpCredential: Boolean = true,
        @JsonProperty("returnSecureToken") val returnSecureToken: Boolean = true)