package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.domain.LoginFirebaseDomain
import br.com.unip.autenticacao.domain.OAuthLoginDomain
import br.com.unip.autenticacao.domain.UsuarioDomain
import br.com.unip.autenticacao.dto.firebase.UsuarioAutenticadoDTO
import br.com.unip.autenticacao.dto.firebase.UsuarioFirebaseResponseDTO

interface IFirebaseRepository {

    fun autenticar(domain : LoginFirebaseDomain) : UsuarioAutenticadoDTO

    fun autenticarViaOAuth(domain : OAuthLoginDomain) : UsuarioAutenticadoDTO

    fun cadastrarUsuario(domain: UsuarioDomain) : UsuarioFirebaseResponseDTO
}