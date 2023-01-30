package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.dto.firebase.LoginDTO

interface ILoginService {

    fun autenticar(dto : LoginDTO) : String

    fun autenticarOAuth(apikey : String) : String
}