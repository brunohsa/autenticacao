package br.com.unip.cardapio.domain.campos

import br.com.unip.cardapio.exception.CampoObrigatorioNaoInformadoException
import br.com.unip.cardapio.exception.ECodigoErro

class TokenDeAcessoOAuth : ICampo<String> {

    val campo: String?

    constructor(campo: String?) {
        try {
            this.campo = CampoObrigatorio(campo).get()
        } catch (ex: CampoObrigatorioNaoInformadoException) {
            throw CampoObrigatorioNaoInformadoException(ECodigoErro.TOKEN_ACESSO_OAUTH)
        }
    }

    override fun get(): String {
        return campo!!
    }
}