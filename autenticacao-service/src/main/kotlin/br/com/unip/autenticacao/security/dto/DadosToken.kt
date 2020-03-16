package br.com.unip.autenticacao.security.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class DadosToken(val claims: Claims, val exp: Long)