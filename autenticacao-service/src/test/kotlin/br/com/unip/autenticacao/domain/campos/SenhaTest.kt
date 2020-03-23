package br.com.unip.autenticacao.domain.campos

import br.com.unip.autenticacao.exception.CampoComFormatoInvalidoException
import org.junit.Assert
import org.junit.Test

class SenhaTest {

    @Test(expected = CampoComFormatoInvalidoException::class)
    fun dadoUmaSenhaSemLetraMinusculaLancaUmaExcecao() {
        val senha = "ABCDEF@1234"
        Senha(senha)
    }

    @Test(expected = CampoComFormatoInvalidoException::class)
    fun dadoUmaSenhaSemLetraMaiusculaLancaUmaExcecao() {
        val senha = "abcdef@1234"
        Senha(senha)
    }

    @Test(expected = CampoComFormatoInvalidoException::class)
    fun dadoUmaSenhaSemCaracterEspecialLancaUmaExcecao() {
        val senha = "abcDEF1234"
        Senha(senha)
    }

    @Test(expected = CampoComFormatoInvalidoException::class)
    fun dadoUmaSenhaSemNumeroLancaUmaExcecao() {
        val senha = "abcDEF@abcDeF"
        Senha(senha)
    }

    @Test(expected = CampoComFormatoInvalidoException::class)
    fun dadoUmaSenhaMenorDoQueOitoCaracteresLancaUmaExcecao() {
        val senha = "aBcD@12"
        Senha(senha)
    }

    @Test
    fun dadoUmaSenhaComOsCriteriosObrigatoriosEntaoRetornaASenhaInformada() {
        val senha = "a12B@cde"
        val senhaValida = Senha(senha)

        Assert.assertNotNull(senhaValida)
        Assert.assertEquals(senha, senhaValida.get())
    }
}