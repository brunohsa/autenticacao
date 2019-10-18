package br.com.unip.cardapio.exception

class CampoInvalidoException : CardapBaseException{

    constructor()

    constructor(codigoErro: ECodigoErro) : super(codigoErro)
}