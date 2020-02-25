package br.com.unip.autenticacao.domain.campos

import br.com.unip.autenticacao.exception.DataPassadaException
import java.time.LocalDate

class DataPassada : ICampo<LocalDate> {

    val data: LocalDate

    constructor(data: String) {
        this.data = dataPassada(Data(data).get())
    }

    override fun get(): LocalDate {
        return data
    }

    private fun dataPassada(dataPassada: LocalDate): LocalDate {
        if (!dataPassada.isBefore(LocalDate.now())) {
            throw DataPassadaException()
        }
        return dataPassada
    }
}