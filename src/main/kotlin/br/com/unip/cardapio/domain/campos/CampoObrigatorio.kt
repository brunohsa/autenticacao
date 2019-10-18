package br.com.unip.cardapio.domain.campos

import br.com.unip.cardapio.exception.CampoObrigatorioNaoInformadoException
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