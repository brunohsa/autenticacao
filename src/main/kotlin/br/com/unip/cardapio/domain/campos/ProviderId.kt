package br.com.unip.cardapio.domain.campos

import br.com.unip.cardapio.exception.CampoObrigatorioNaoInformadoException
import br.com.unip.cardapio.exception.ECodigoErro

class ProviderId : ICampo<String> {

    val campo: String?

    constructor(campo: String?) {
        try {
            this.campo = CampoObrigatorio(campo).get()
        } catch (ex: CampoObrigatorioNaoInformadoException) {
            throw CampoObrigatorioNaoInformadoException(ECodigoErro.REQUEST_URI)
        }
    }

    override fun get(): String {
        return campo!!
    }
}