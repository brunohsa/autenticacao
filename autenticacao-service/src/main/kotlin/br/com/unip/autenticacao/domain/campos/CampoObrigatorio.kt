package br.com.unip.autenticacao.domain.campos

import br.com.unip.autenticacao.exception.CampoObrigatorioNaoInformadoException
import org.springframework.util.StringUtils

class CampoObrigatorio<T> : ICampo<T> {

    val campo: T

    constructor(campo: T) {
        if (StringUtils.isEmpty(campo)) {
            throw CampoObrigatorioNaoInformadoException()
        }
        this.campo = campo
    }

    override fun get(): T {
        return campo
    }
}