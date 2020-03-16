package br.com.unip.autenticacao.domain.campos

import br.com.unip.autenticacao.exception.CampoObrigatorioNaoInformadoException
import org.junit.Test

class CampoObrigatorioTest {

    @Test(expected = CampoObrigatorioNaoInformadoException::class)
    fun dadoUmCampoObrigatorioNuloLancaUmaExcecao() {
        val campo = null
        CampoObrigatorio(campo)
    }

    @Test(expected = CampoObrigatorioNaoInformadoException::class)
    fun dadoUmCampoObrigatorioVazioLancaUmaExcecao() {
        val campo = ""
        CampoObrigatorio(campo)
    }
}