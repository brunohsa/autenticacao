package br.com.unip.autenticacao.exception

class DataPassadaException : AutenticacaoBaseException{

    constructor()

    constructor(codigoErro: ECodigoErro) : super(codigoErro)
}