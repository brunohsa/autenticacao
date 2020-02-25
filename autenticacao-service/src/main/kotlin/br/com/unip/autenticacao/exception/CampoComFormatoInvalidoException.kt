package br.com.unip.autenticacao.exception

class CampoComFormatoInvalidoException : CardapBaseException {

    constructor()

    constructor(codigoErro: ECodigoErro) : super(codigoErro)
}