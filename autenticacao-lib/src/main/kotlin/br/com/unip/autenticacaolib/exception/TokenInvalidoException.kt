package br.com.unip.autenticacaolib.exception

import br.com.unip.autenticacaolib.exception.ECodigoErro.TOKEN_INVALIDO

class TokenInvalidoException : BaseException {

    constructor() : super(TOKEN_INVALIDO)
}