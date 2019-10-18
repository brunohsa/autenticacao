package br.com.unip.cardapio.domain.campos

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class Data : ICampo<LocalDate> {

    private val DATE_FORMAT: String = "dd/MM/yyyy"

    val data: String

    constructor(data: String) {
        this.data = data
    }

    override fun get(): LocalDate {
        try {
            val format = DateTimeFormatter.ofPattern(DATE_FORMAT)
            return LocalDate.parse(data, format)
        } catch (ex: DateTimeParseException) {
            throw RuntimeException()
        }
    }
}