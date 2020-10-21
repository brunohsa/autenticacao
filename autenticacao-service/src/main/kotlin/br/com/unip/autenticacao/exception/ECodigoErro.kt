package br.com.unip.autenticacao.exception

enum class ECodigoErro {

    ERRO_INESPERADO("000"),
    ACESSO_NEGADO("001"),
    TOKEN_INVALIDO("002"),
    TOKEN_EXPIRADO("003"),
    CAMPO_OBRIGATORIO_NAO_INFORMADO("004"),
    DATA_NAO_RETROATIVA("005"),
    CAMPO_COM_FORMATO_INVALIDO("006"),
    CAMPO_INVALIDO("007"),
    EMAIL_NAO_INFORMADO("008"),
    EMAIL_INVALIDO("009"),
    SENHA_NAO_ATENDE_OS_REQUISITOS("010"),
    USUARIO_JA_CADASTRADO("011"),
    DADOS_DA_PESSOA_SAO_OBRIGATORIOS("012"),


    //ERROS FIREBASE
    INVALID_PASSWORD("090"),
    TOO_MANY_ATTEMPTS_TRY_LATER("091");

    val codigo: String

    constructor(erro: String) {
        this.codigo = erro
    }
}