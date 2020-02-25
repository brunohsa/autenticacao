package br.com.unip.autenticacaolib

import br.com.unip.autenticacaolib.dto.Claims
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

class AuthenticationToken : UsernamePasswordAuthenticationToken {

    val email: String
    val cadastroUUID: String
    val usuarioUUID: String

    constructor(claims: Claims, autorizacoes: List<SimpleGrantedAuthority>) :
            super(claims.email!!, null, autorizacoes) {
        this.email = claims.email!!
        this.usuarioUUID = claims.usuarioUUID!!
        this.cadastroUUID = claims.cadastroUUID!!
    }
}