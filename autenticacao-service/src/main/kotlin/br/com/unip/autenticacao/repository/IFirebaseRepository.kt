package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.domain.LoginFirebaseDomain
import br.com.unip.autenticacao.domain.UsuarioDomain
import br.com.unip.autenticacao.dto.firebase.UsuarioAutenticadoDTO

interface IFirebaseRepository {

    fun autenticar(domain : LoginFirebaseDomain) : UsuarioAutenticadoDTO

    fun autenticarOAuth(token: String, provider: String) : UsuarioAutenticadoDTO

    fun cadastrarUsuario(domain: UsuarioDomain)
}