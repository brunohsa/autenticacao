package br.com.unip.autenticacao.dto

class UsuarioDTO {

    var id: Long? = null
    val email: String?
    var senha: String? = null
    var pessoa: IPessoaDTO? = null
    var uuid: String? = null
    var cadastroUUID: String? = null

    constructor(email: String?, senha: String?, pessoa: IPessoaDTO) : this(email, pessoa) {
        this.senha = senha
    }

    constructor(id: Long, uuid: String, email: String, senha: String) : this(uuid, email) {
        this.id = id
        this.senha = senha
    }


    constructor(uuid: String?, email: String?, cadastroUUID: String?) : this(uuid, email) {
        this.cadastroUUID = cadastroUUID
    }

    constructor(uuid: String?, email: String?) {
        this.uuid = uuid
        this.email = email
    }

    constructor(email: String?, pessoa: IPessoaDTO) {
        this.email = email
        this.pessoa = pessoa
    }
}