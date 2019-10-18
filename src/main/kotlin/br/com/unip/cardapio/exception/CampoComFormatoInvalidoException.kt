package br.com.unip.cardapio.exception

class CampoComFormatoInvalidoException : CardapBaseException {

    constructor()

    constructor(codigoErro: ECodigoErro) : super(codigoErro)
}