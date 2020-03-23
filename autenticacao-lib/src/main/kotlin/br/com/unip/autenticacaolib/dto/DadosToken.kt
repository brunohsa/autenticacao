package br.com.unip.autenticacaolib.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class DadosToken {

    @JsonProperty(value = "claims")
    lateinit var claims: Claims

    @JsonProperty(value = "exp")
    var expiration: Long = 0
}