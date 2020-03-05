package br.com.unip.autenticacao.webservice.model.response

import br.com.unip.autenticacao.exception.ECodigoErro
import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class Erro {

    @JsonProperty(value = "codigo")
    private lateinit var codigoErro: ECodigoErro

    @JsonProperty(value = "mensagem")
    private var mensagem: String? = ""

    constructor()

    constructor(codigo: ECodigoErro, mensagem: String?) {
        this.codigoErro = codigo
        this.mensagem = mensagem
    }

    @JsonGetter(value = "codigo")
    fun getCodigo(): String {
        return codigoErro.codigo
    }

    fun getMensagem(): String? {
        return mensagem
    }

}