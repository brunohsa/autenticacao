package br.com.unip.cardapio.util

import br.com.unip.cardapio.dto.firebase.UsuarioAutenticadoDTO
import br.com.unip.cardapio.exception.TokenExpiradoException
import br.com.unip.cardapio.exception.TokenInvalidoException
import br.com.unip.cardapio.service.IUsuarioService
import io.jsonwebtoken.CompressionCodecs
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class TokenUtil(val usuarioService: IUsuarioService) {

    private val TOKEN_TAG = "token"

    @Value("\${autenticacao.key}")
    private val key: String? = null

    fun gerarToken(usuarioAutenticado: UsuarioAutenticadoDTO): String {
        val dateExpiration = LocalDateTime.now().plusSeconds(usuarioAutenticado.expiresIn)

        val claims = Jwts.claims()
        claims.subject = usuarioAutenticado.email
        claims.issuer = usuarioAutenticado.localId
        claims.issuedAt = Date(System.currentTimeMillis())
        claims.expiration = Timestamp.valueOf(dateExpiration)
        claims["scopes"] = this.buscarPremissoes(usuarioAutenticado)

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, key)
                .compressWith(CompressionCodecs.DEFLATE).compact()
    }

    private fun buscarPremissoes(usuarioAutenticado: UsuarioAutenticadoDTO) : Set<SimpleGrantedAuthority>{
        return usuarioService.buscarPermissoes(usuarioAutenticado.email)
                .map { permissao -> SimpleGrantedAuthority(permissao.nome) }
                .toSet()
    }

    @Throws(TokenExpiradoException::class, TokenInvalidoException::class)
    fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken {
        val token = request.getHeader(TOKEN_TAG)
        if (token.isNullOrEmpty()) {
            throw TokenInvalidoException()
        }
        try {
            val user = Jwts.parser().setSigningKey(key).parseClaimsJws(token).body.subject
            return UsernamePasswordAuthenticationToken(user, null, Collections.emptyList())
        } catch (e: ExpiredJwtException) {
            throw TokenExpiradoException()
        }
    }
}