package br.com.unip.cardapio.exception

enum class ECodigoErro {

    TOKEN_INVALIDO("0000"),
    TOKEN_EXPIRADO("0001"),
    EMAIL_NAO_INFORMADO("0002"),
    EMAIL_INVALIDO("0003"),
    SENHA_NAO_INFORMADA("0004"),
    SENHA_NAO_ATENDE_OS_REQUISITOS("0005"),
    USUARIO_JA_CADASTRADO("0006"),
    TOKEN_ACESSO_OAUTH("0007"),
    PROVIDER_ID("0008"),
    REQUEST_URI("0009");

    val codigo: String

    constructor(erro: String) {
        this.codigo = erro
    }
}