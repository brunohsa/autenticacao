package br.com.unip.autenticacao.exception

class CampoInvalidoException : CardapBaseException{

    constructor()

    constructor(codigoErro: ECodigoErro) : super(codigoErro)
}