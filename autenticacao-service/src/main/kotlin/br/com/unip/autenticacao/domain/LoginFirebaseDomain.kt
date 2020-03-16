package br.com.unip.autenticacao.domain

import br.com.unip.autenticacao.domain.campos.CampoObrigatorio
import br.com.unip.autenticacao.domain.campos.Email

class LoginFirebaseDomain {

    val email: Email
    val senha: CampoObrigatorio<String>

    constructor(email: String, senha: String) {
        this.email = Email(email)
        this.senha = CampoObrigatorio(senha)
    }
}