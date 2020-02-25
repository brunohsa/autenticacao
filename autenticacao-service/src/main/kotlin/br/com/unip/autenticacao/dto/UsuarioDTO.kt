package br.com.unip.autenticacao.dto

class UsuarioDTO {

    var id: Long? = null
    val email: String?
    var senha: String? = null
    var pessoa: IPessoaDTO? = null
    var uuid : String? = null
    var cadastroUUID : String? = null

    constructor(email: String?,
                senha: String?,
                pessoa: IPessoaDTO) {
        this.email = email
        this.senha = senha
        this.pessoa = pessoa
    }

    constructor(id: Long,
                email: String,
                senha: String) {
        this.id = id
        this.senha = senha
        this.email = email
    }

    constructor(email: String) {
        this.email = email
    }

    constructor(uuid: String?, email: String?, cadastroUUID: String?) {
        this.uuid = uuid
        this.email = email
        this.cadastroUUID = cadastroUUID
    }
}