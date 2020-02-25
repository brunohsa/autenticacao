package br.com.unip.autenticacaolib.exception

enum class ECodigoErro {

    TOKEN_INVALIDO("0000"),
    TOKEN_EXPIRADO("0001");

    val codigo: String

    constructor(erro: String) {
        this.codigo = erro
    }
}