package br.com.unip.autenticacaolib.util

import br.com.unip.autenticacaolib.AuthenticationToken
import br.com.unip.autenticacaolib.dto.DadosToken
import br.com.unip.autenticacaolib.exception.TokenExpiradoException
import br.com.unip.autenticacaolib.exception.TokenInvalidoException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.Base64
import javax.servlet.http.HttpServletRequest

class TokenUtil {

    private val TOKEN_TAG = "token"

    @Throws(TokenExpiradoException::class, TokenInvalidoException::class)
    fun getAuthentication(request: HttpServletRequest): AuthenticationToken {
        val token = request.getHeader(TOKEN_TAG)
        if (token.isNullOrEmpty()) {
            throw TokenInvalidoException()
        }
        val splitString = token.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val base64EncodedBody = splitString[1]

        val decoder = Base64.getDecoder()
        val body = String(decoder.decode(base64EncodedBody))

        val dadosToken = ObjectMapper().readValue(body, DadosToken::class.java)
        this.verificarSeTokenExpirado(dadosToken)

        val autorizacoes = this.getAutorizacoes(dadosToken)
        return AuthenticationToken(dadosToken.getClaims(), autorizacoes)
    }

    private fun verificarSeTokenExpirado(dadosToken: DadosToken) {
        val time = dadosToken.expiration * 1000
        var dateExpiracao = Timestamp(time).toLocalDateTime()
        if (LocalDateTime.now().isAfter(dateExpiracao)) {
            throw TokenExpiradoException()
        }
    }

    private fun getAutorizacoes(dadosToken: DadosToken): List<SimpleGrantedAuthority> {
        val scopes = dadosToken.getClaims().scopes
        return scopes.map { s -> SimpleGrantedAuthority(s) }.toList()
    }
}