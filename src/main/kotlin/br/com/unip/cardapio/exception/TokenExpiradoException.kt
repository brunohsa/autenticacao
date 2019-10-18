package br.com.unip.cardapio.exception

class TokenExpiradoException : CardapBaseException {

    constructor() : super(ECodigoErro.TOKEN_EXPIRADO)
}