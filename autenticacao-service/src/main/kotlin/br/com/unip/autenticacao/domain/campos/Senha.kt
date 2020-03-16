package br.com.unip.autenticacao.domain.campos

import br.com.unip.autenticacao.exception.CampoComFormatoInvalidoException
import br.com.unip.autenticacao.exception.ECodigoErro.SENHA_NAO_ATENDE_OS_REQUISITOS

class Senha : ICampo<String> {

    /*
        deve conter ao menos um digito
        deve conter ao menos uma letra minuscula
        deve conter ao menos uma letra maiúscula
        deve conter ao menos um caractere especial
        deve conter ao menos 8 dos caracteres mencionados
     */
    val REGEX_SENHA_FORTE = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!:()\\-_?&])(?=\\S+\$).{8,}".toRegex()

    val campo: String?

    constructor(campo: String?) {
        try {
            this.campo = campo
            this.validarSenha(campo)
        } catch (ex: CampoComFormatoInvalidoException) {
            throw CampoComFormatoInvalidoException(SENHA_NAO_ATENDE_OS_REQUISITOS)
        }
    }

    override fun get(): String {
        return campo!!
    }

    private fun validarSenha(senha: String?) {
        if (!senha.isNullOrEmpty() && !senhaValida(senha)) {
            throw CampoComFormatoInvalidoException()
        }
    }

    private fun senhaValida(senha: String): Boolean {
        return REGEX_SENHA_FORTE.matches(senha)
    }
}