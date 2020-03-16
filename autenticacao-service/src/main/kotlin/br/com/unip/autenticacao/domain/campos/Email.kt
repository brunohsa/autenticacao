package br.com.unip.autenticacao.domain.campos

import br.com.unip.autenticacao.exception.CampoComFormatoInvalidoException
import br.com.unip.autenticacao.exception.CampoObrigatorioNaoInformadoException
import br.com.unip.autenticacao.exception.ECodigoErro.EMAIL_INVALIDO
import br.com.unip.autenticacao.exception.ECodigoErro.EMAIL_NAO_INFORMADO

class Email : ICampo<String> {

    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})".toRegex()

    val campo: String?

    constructor(campo: String?) {
        try {
            this.campo = CampoObrigatorio(campo).get()
            this.validarEmail(campo!!)
        } catch (ex: CampoObrigatorioNaoInformadoException) {
            throw CampoObrigatorioNaoInformadoException(EMAIL_NAO_INFORMADO)
        } catch (ex: CampoComFormatoInvalidoException) {
            throw CampoComFormatoInvalidoException(EMAIL_INVALIDO)
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