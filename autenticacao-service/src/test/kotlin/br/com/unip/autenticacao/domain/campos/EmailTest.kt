package br.com.unip.autenticacao.domain.campos

import br.com.unip.autenticacao.exception.CampoComFormatoInvalidoException
import br.com.unip.autenticacao.exception.CampoObrigatorioNaoInformadoException
import org.junit.Assert
import org.junit.Test

class EmailTest {

    @Test(expected = CampoObrigatorioNaoInformadoException::class)
    fun dadoUmEmailVazioLancaUmaExcecao() {
        val email = ""
        Email(email)
    }

    @Test(expected = CampoObrigatorioNaoInformadoException::class)
    fun dadoUmEmailNuloLancaUmaExcecao() {
        val email = null
        Email(email)
    }

    @Test(expected = CampoComFormatoInvalidoException::class)
    fun dadoUmEmailSemPontoLancaUmaExcecao() {
        val email = "email@teste"
        Email(email)
    }

    @Test(expected = CampoComFormatoInvalidoException::class)
    fun dadoUmEmailSemOArrobaLancaUmaExcecao() {
        val email = "email.teste.com"
        Email(email)
    }

    @Test
    fun dadoUmEmailValidoEntaoRetornaOEmailInformado() {
        val email = "email@teste.com.br"
        val emailValidado = Email(email)

        Assert.assertNotNull(emailValidado)
        Assert.assertEquals(email, emailValidado.get())
    }
}