package br.com.unip.cardapio.domain.campos

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
            throw RuntimeException()
        }
        return dataPassada
    }
}