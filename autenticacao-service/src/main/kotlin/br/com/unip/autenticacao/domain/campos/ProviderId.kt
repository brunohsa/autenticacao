package br.com.unip.autenticacao.domain.campos

import br.com.unip.autenticacao.exception.CampoObrigatorioNaoInformadoException
import br.com.unip.autenticacao.exception.ECodigoErro.REQUEST_URI

class ProviderId : ICampo<String> {

    val campo: String?

    constructor(campo: String?) {
        try {
            this.campo = CampoObrigatorio(campo).get()
        } catch (ex: CampoObrigatorioNaoInformadoException) {
            throw CampoObrigatorioNaoInformadoException(REQUEST_URI)
        }
    }

    override fun get(): String {
        return campo!!
    }
}