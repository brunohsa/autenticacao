package br.com.unip.cardapio.repository

import br.com.unip.cardapio.domain.LoginFirebaseDomain
import br.com.unip.cardapio.domain.OAuthLoginDomain
import br.com.unip.cardapio.domain.UsuarioDomain
import br.com.unip.cardapio.dto.firebase.UsuarioAutenticadoDTO
import br.com.unip.cardapio.dto.firebase.UsuarioFirebaseResponseDTO

interface IFirebaseRepository {

    fun autenticar(domain : LoginFirebaseDomain) : UsuarioAutenticadoDTO

    fun autenticarViaOAuth(domain : OAuthLoginDomain) : UsuarioAutenticadoDTO

    fun cadastrarUsuario(domain: UsuarioDomain) : UsuarioFirebaseResponseDTO
}