package br.com.unip.cardapio.domain.campos

class CampoNumerico : ICampo<String> {

    private val REGEX_NUMEROS: Regex =  "-?\\d+(\\.\\d+)?".toRegex()
    private val valor: String

    constructor(valor: ICampo<String>) {
        if (!valor.get().matches(REGEX_NUMEROS)) {
            throw RuntimeException()
        }
        this.valor = valor.get()!!
    }

    override fun get(): String {
        return valor
    }
}