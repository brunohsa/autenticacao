package br.com.unip.autenticacao.repository.entity.enums

enum class EPerfis {

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