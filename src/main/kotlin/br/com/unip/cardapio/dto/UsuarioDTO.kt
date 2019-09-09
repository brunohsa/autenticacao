package br.com.unip.cardapio.dto

class UsuarioDTO {

    var id: Long? = null
    val username: String
    val password: String
    val ativo: Boolean
    var uuidPessoa: String = ""

    constructor(id: Long,
                username: String,
                password: String,
                ativo: Boolean) : this(username, password, "", ativo) {
        this.id = id
    }

    constructor(username: String,
                password: String,
                uuidPessoa: String,
                ativo: Boolean) {
        this.username = username
        this.password = password
        this.ativo = ativo
        this.uuidPessoa = uuidPessoa
    }

}