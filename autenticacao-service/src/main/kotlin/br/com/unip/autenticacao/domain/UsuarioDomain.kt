package br.com.unip.autenticacao.domain

import br.com.unip.autenticacao.domain.campos.Email
import br.com.unip.autenticacao.domain.campos.Senha
import br.com.unip.autenticacao.repository.entity.enums.ESituacaoUsuario


class UsuarioDomain {

    val email: Email
    val senha: Senha
    val situacao: ESituacaoUsuario
    val perfis: List<String>

    constructor(email: String?,
                senha: String?,
                situacao: ESituacaoUsuario,
                perfis: List<String>) {
        this.email = Email(email)
        this.senha = Senha(senha)
        this.situacao = situacao
        this.perfis = perfis
    }
}