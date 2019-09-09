package br.com.unip.cardapio.service

import br.com.unip.cardapio.security.dto.AuthenticatedUser
import br.com.unip.cardapio.security.dto.DatabaseUsernamePasswordAuthentication

interface IDatabaseAuthenticationService {

    fun authenticate(user: DatabaseUsernamePasswordAuthentication): AuthenticatedUser
}