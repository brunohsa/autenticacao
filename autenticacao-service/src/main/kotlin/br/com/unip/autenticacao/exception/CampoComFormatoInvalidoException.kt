package br.com.unip.autenticacao.exception

import br.com.unip.autenticacao.exception.ECodigoErro.CAMPO_COM_FORMATO_INVALIDO
import org.springframework.http.HttpStatus.BAD_REQUEST

class CampoComFormatoInvalidoException : AutenticacaoBaseException {

    constructor() : this(CAMPO_COM_FORMATO_INVALIDO)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, BAD_REQUEST)
}