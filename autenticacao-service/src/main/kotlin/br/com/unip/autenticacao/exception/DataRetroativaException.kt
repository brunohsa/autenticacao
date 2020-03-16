package br.com.unip.autenticacao.exception

import br.com.unip.autenticacao.exception.ECodigoErro.DATA_NAO_RETROATIVA

class DataRetroativaException : CampoInvalidoException {

    constructor() : this(DATA_NAO_RETROATIVA)

    constructor(codigoErro: ECodigoErro) : super(codigoErro)
}