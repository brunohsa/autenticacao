package br.com.unip.autenticacao.webservice.model.response.erro

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseError {

    @JsonProperty("erro")
    lateinit var erro: Erro

    constructor()

    constructor(erro: Erro) {
        this.erro = erro
    }
}