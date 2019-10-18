package br.com.unip.cardapio.domain.campos

import br.com.unip.cardapio.exception.CampoObrigatorioNaoInformadoException
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