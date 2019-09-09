package br.com.unip.cardapio.security.provider

import br.com.unip.cardapio.security.dto.DatabaseUsernamePasswordAuthentication
import br.com.unip.cardapio.service.IDatabaseAuthenticationService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication

class DatabaseAuthenticationProvider(val authenticationService: IDatabaseAuthenticationService)
    : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val auth = authentication as DatabaseUsernamePasswordAuthentication
        val usuarioAutenticado = this.authenticationService.authenticate(auth)

        return DatabaseUsernamePasswordAuthentication(usuarioAutenticado, usuarioAutenticado.password,
                usuarioAutenticado.roles)
    }

    override fun supports(aClass: Class<*>): Boolean {
        return DatabaseUsernamePasswordAuthentication::class.java.isAssignableFrom(aClass)
    }
}