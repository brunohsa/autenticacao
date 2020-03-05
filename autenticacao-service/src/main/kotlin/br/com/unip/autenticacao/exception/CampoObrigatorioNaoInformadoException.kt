package br.com.unip.autenticacao.exception

import br.com.unip.autenticacao.exception.ECodigoErro.CAMPO_OBRIGATORIO_NAO_INFORMADO
import org.springframework.http.HttpStatus.BAD_REQUEST

class CampoObrigatorioNaoInformadoException : AutenticacaoBaseException {

    constructor() : this(CAMPO_OBRIGATORIO_NAO_INFORMADO)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, BAD_REQUEST)
}