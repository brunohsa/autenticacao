package br.com.unip.autenticacao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class PessoaFisicaDTO : IPessoaDTO {

    @JsonProperty("nome")
    var nome: String? = ""

    @JsonProperty("sobrenome")
    var sobrenome: String? = ""

    @JsonProperty("telefone")
    var telefone: String? = ""

    @JsonProperty("data_nascimento")
    var dataNascimento: String? = ""

    @JsonProperty("cpf")
    var cpf: String? = ""

    constructor()

    constructor(nome: String?, sobrenome: String?, telefone: String?, dataNascimento: String?, cpf: String?) :
            this(nome, sobrenome) {
        this.telefone = telefone
        this.dataNascimento = dataNascimento
        this.cpf = cpf
    }

    constructor(nome: String?, sobrenome: String?) {
        this.nome = nome
        this.sobrenome = sobrenome
    }
}