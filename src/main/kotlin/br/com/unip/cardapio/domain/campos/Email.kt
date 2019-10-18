package br.com.unip.cardapio.domain.campos

import br.com.unip.cardapio.exception.CampoComFormatoInvalidoException
import br.com.unip.cardapio.exception.CampoObrigatorioNaoInformadoException
import br.com.unip.cardapio.exception.ECodigoErro

class Email : ICampo<String> {

    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})".toRegex()

    val campo: String?

    constructor(campo: String?) {
        try {
            this.campo = CampoObrigatorio(campo).get()
            this.validarEmail(campo!!)
        } catch (ex: CampoObrigatorioNaoInformadoException) {
            throw CampoObrigatorioNaoInformadoException(ECodigoErro.EMAIL_NAO_INFORMADO)
        } catch (ex: CampoComFormatoInvalidoException) {
            throw CampoComFormatoInvalidoException(ECodigoErro.EMAIL_INVALIDO)
        }
    }

    override fun get(): String {
        return campo!!
    }

    private fun validarEmail(email: String) {
        if (!emailValido(email)) {
            throw CampoComFormatoInvalidoException()
        }
    }

    private fun emailValido(email: String): Boolean {
        return EMAIL_REGEX.matches(email)
    }
}