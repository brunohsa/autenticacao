package br.com.unip.autenticacao.exception

import br.com.unip.autenticacao.exception.ECodigoErro.TOKEN_EXPIRADO

class TokenExpiradoException : CardapBaseException {

    constructor() : super(TOKEN_EXPIRADO)
}