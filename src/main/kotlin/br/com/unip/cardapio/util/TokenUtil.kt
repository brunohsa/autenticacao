package br.com.unip.cardapio.util

import br.com.unip.cardapio.security.dto.AuthenticatedUser
import io.jsonwebtoken.CompressionCodecs
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenUtil {

    @Value("\${autenticacao.key}")
    private val key: String? = null

    @Value("\${tempo.expiracao}")
    private val authExpirationTime: String? = null

    fun gerarToken(usuarioAutenticado: AuthenticatedUser): String {
        val dateExpiration = Date(System.currentTimeMillis() + java.lang.Long.parseLong(authExpirationTime!!))

        val claims = Jwts.claims()
        claims.subject = usuarioAutenticado.name
        claims.issuer = usuarioAutenticado.id.toString()
        claims.issuedAt = Date(System.currentTimeMillis())
        claims.expiration = dateExpiration
        claims["scopes"] = usuarioAutenticado.roles

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, key)
                .compressWith(CompressionCodecs.DEFLATE).compact()
    }
}