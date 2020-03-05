package br.com.unip.autenticacao.webservice.model.response.erro

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class Erro {

    @JsonProperty(value = "codigo")
    lateinit var codigoErro: String

    @JsonProperty(value = "mensagem")
    var mensagem: String? = ""

    @JsonProperty(value = "microservico")
    private var microservice: EMicroservice = EMicroservice.AUTENTICACAO

    constructor()

    constructor(codigo: String, mensagem: String?) {
        this.codigoErro = codigo
        this.mensagem = mensagem
    }

}