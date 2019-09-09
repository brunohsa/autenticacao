package br.com.unip.cardapio

class UsuarioDomain {

    val username: String
    val password: String
    val uuidPessoa: String
    val ativo: Boolean

    constructor(username: String,
                password: String,
                uuidPessoa: String,
                ativo: Boolean) {
        this.username = username
        this.password = password
        this.uuidPessoa = uuidPessoa
        this.ativo = ativo
    }
}