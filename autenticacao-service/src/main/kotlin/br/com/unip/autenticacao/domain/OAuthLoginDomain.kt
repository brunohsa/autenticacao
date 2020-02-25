package br.com.unip.autenticacao.domain

import br.com.unip.autenticacao.domain.campos.ProviderId
import br.com.unip.autenticacao.domain.campos.RequestURI
import br.com.unip.autenticacao.domain.campos.TokenDeAcessoOAuth

class OAuthLoginDomain {

    val tokenDeAcesso: TokenDeAcessoOAuth
    val providerId: ProviderId
    val requestUri: RequestURI

    constructor(tokenDeAcesso: String, providerId: String, requestUri: String) {
        this.tokenDeAcesso = TokenDeAcessoOAuth(tokenDeAcesso)
        this.providerId = ProviderId(providerId)
        this.requestUri = RequestURI(requestUri)
    }
}