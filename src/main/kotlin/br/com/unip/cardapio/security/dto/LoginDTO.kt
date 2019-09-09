package br.com.confidencecambio.auth.dto

class LoginDTO {

    var usuario: String = ""
    var senha: String = ""

    constructor() {}

    constructor(usuario: String, senha: String) {
        this.usuario = usuario
        this.senha = senha
    }
}