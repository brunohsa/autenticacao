package br.com.unip.autenticacao.exception

import br.com.unip.autenticacao.exception.ECodigoErro.TOKEN_INVALIDO

class TokenInvalidoException : CardapBaseException {

    constructor() : super(TOKEN_INVALIDO)
}