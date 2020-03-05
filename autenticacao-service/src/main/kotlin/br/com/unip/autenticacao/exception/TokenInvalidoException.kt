package br.com.unip.autenticacao.exception

import br.com.unip.autenticacao.exception.ECodigoErro.TOKEN_INVALIDO
import org.springframework.http.HttpStatus

class TokenInvalidoException : AutenticacaoBaseException {

    constructor() : super(TOKEN_INVALIDO, HttpStatus.UNAUTHORIZED)
}