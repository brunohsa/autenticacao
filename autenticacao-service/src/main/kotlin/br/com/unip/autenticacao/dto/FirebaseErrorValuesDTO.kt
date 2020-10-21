package br.com.unip.autenticacao.dto

class FirebaseErrorValuesDTO {

    var code: String = ""
    var message: String? = ""

    constructor()

    constructor(code: String, message: String) {
        this.code = code
        this.message = message
    }
}