package br.com.unip.autenticacao.exception

class DataPassadaException : CardapBaseException{

    constructor()

    constructor(codigoErro: ECodigoErro) : super(codigoErro)
}