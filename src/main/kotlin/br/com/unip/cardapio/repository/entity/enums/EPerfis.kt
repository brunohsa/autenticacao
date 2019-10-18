package br.com.unip.cardapio.repository.entity.enums

enum class EPerfis {

    ADMINISTRADOR,
    SISTEMA,
    CONSUMIDOR,
    FORNECEDOR;

    companion object {
        fun perfisConsumidor(): List<String> {
            return listOf(CONSUMIDOR.name)
        }

        fun perfisFornecedor(): List<String> {
            return listOf(FORNECEDOR.name)
        }
    }
}