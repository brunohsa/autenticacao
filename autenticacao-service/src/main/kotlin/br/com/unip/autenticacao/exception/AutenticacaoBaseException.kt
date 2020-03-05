package br.com.unip.autenticacao.exception

import org.springframework.http.HttpStatus

open class AutenticacaoBaseException : RuntimeException {

    lateinit var codigoErro: ECodigoErro
    lateinit var httpStatus: HttpStatus

    constructor()

    constructor(message: String?, codigoErro: ECodigoErro, httpStatus: HttpStatus) : super(message) {
        this.codigoErro = codigoErro
        this.httpStatus = httpStatus
    }

    constructor(codigoErro: ECodigoErro, httpStatus: HttpStatus) {
        this.codigoErro = codigoErro
        this.httpStatus = httpStatus
    }
}