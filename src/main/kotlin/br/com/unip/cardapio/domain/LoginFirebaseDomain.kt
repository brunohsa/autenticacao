package br.com.unip.cardapio.domain

import br.com.unip.cardapio.domain.campos.Email
import br.com.unip.cardapio.domain.campos.Senha

class LoginFirebaseDomain {

    val email: Email
    val senha: Senha

    constructor(email: String, senha: String) {
        this.email = Email(email)
        this.senha = Senha(senha)
    }
}