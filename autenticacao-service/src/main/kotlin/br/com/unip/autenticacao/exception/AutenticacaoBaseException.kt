package br.com.unip.autenticacao.exception

open class CardapBaseException : RuntimeException {

    lateinit var codigoErro: ECodigoErro

    constructor()

    constructor(message: String?, codigoErro: ECodigoErro) : super(message) {
        this.codigoErro = codigoErro
    }

    constructor(codigoErro: ECodigoErro) {
        this.codigoErro = codigoErro
    }
}