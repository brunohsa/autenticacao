package br.com.unip.autenticacao.exception

import br.com.unip.autenticacao.exception.ECodigoErro.CAMPO_INVALIDO
import org.springframework.http.HttpStatus.BAD_REQUEST

open class CampoInvalidoException : AutenticacaoBaseException{

    constructor() : this(CAMPO_INVALIDO)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, BAD_REQUEST)
}