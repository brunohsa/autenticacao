package br.com.unip.cardapio.exception

class CampoObrigatorioNaoInformadoException : CardapBaseException {

    constructor()
    
    constructor(message: String?, codigoErro: ECodigoErro) : super(message, codigoErro)

    constructor(codigoErro: ECodigoErro) : super(codigoErro)
}