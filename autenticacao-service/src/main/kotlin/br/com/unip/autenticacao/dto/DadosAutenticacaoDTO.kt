package br.com.unip.autenticacao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class DadosAutenticacaoDTO(val email: String, var cadastroUUID: String?, val token: String)
