package br.com.unip.autenticacao.domain.campos

import br.com.unip.autenticacao.exception.CampoObrigatorioNaoInformadoException
import br.com.unip.autenticacao.exception.ECodigoErro.TOKEN_ACESSO_OAUTH

class TokenDeAcessoOAuth : ICampo<String> {

    val campo: String?

    constructor(campo: String?) {
        try {
            this.campo = CampoObrigatorio(campo).get()
        } catch (ex: CampoObrigatorioNaoInformadoException) {
            throw CampoObrigatorioNaoInformadoException(TOKEN_ACESSO_OAUTH)
        }
    }

    override fun get(): String {
        return campo!!
    }
}