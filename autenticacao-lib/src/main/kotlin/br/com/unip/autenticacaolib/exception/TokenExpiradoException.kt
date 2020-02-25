package br.com.unip.autenticacaolib.exception

import br.com.unip.autenticacaolib.exception.ECodigoErro.TOKEN_EXPIRADO

class TokenExpiradoException : BaseException {

    constructor() : super(TOKEN_EXPIRADO)
}