package br.com.unip.cardapio.security.dto

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class DatabaseUsernamePasswordAuthentication : UsernamePasswordAuthenticationToken {

    constructor(principal: Any,
                credentials: Any) : super(principal, credentials)

    constructor(principal: Any,
                credentials: Any,
                authorities: Collection<GrantedAuthority>) : super(principal, credentials, authorities)
}