package br.com.unip.cardapio.service

import br.com.unip.cardapio.dto.firebase.LoginDTO
import br.com.unip.cardapio.dto.firebase.OAuthLoginFirebaseDTO

interface ILoginService {

    fun autenticar(dto : LoginDTO) : String

    fun autenticarViaOAuth(dto : OAuthLoginFirebaseDTO) : String
}