package br.com.unip.autenticacao.security.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Claims(val scopes: List<String>, val email: String)