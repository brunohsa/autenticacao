package br.com.unip.autenticacao.exception

import br.com.unip.autenticacao.exception.ECodigoErro.TOKEN_EXPIRADO
import org.springframework.http.HttpStatus.FORBIDDEN

class TokenExpiradoException : AutenticacaoBaseException {

    constructor() : super(TOKEN_EXPIRADO, FORBIDDEN)
}