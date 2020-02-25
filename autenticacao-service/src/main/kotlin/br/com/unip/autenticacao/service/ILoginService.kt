package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.dto.firebase.LoginDTO
import br.com.unip.autenticacao.dto.firebase.OAuthLoginFirebaseDTO

interface ILoginService {

    fun autenticar(dto : LoginDTO) : String

    fun autenticarViaOAuth(dto : OAuthLoginFirebaseDTO) : String
}