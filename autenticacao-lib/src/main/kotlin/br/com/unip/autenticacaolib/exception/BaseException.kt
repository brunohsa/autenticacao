package br.com.unip.autenticacaolib.exception

open class BaseException : RuntimeException {

    var codigoErro: ECodigoErro? = null

    constructor()

    constructor(message: String?, codigoErro: ECodigoErro) : super(message) {
        this.codigoErro = codigoErro
    }

    constructor(codigoErro: ECodigoErro) {
        this.codigoErro = codigoErro
    }
}