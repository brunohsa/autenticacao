package br.com.unip.cardapio.exception

open class CardapBaseException : RuntimeException {

    var codigoErro: ECodigoErro? = null

    constructor()

    constructor(message: String?, codigoErro: ECodigoErro) : super(message) {
        this.codigoErro = codigoErro
    }

    constructor(codigoErro: ECodigoErro) {
        this.codigoErro = codigoErro
    }
}