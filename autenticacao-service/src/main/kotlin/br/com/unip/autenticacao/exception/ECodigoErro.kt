package br.com.unip.autenticacao.exception

enum class ECodigoErro {

    EMAIL_NAO_INFORMADO("0000"),
    EMAIL_INVALIDO("0001"),
    SENHA_NAO_ATENDE_OS_REQUISITOS("0002"),
    USUARIO_JA_CADASTRADO("0003"),
    TOKEN_ACESSO_OAUTH("0004"),
    REQUEST_URI("0005"),
    TOKEN_INVALIDO("0006"),
    TOKEN_EXPIRADO("0007");


    val codigo: String

    constructor(erro: String) {
        this.codigo = erro
    }
}