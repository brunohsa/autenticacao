package br.com.unip.autenticacaolib.util

import br.com.unip.autenticacaolib.AuthenticationConfig
import br.com.unip.autenticacaolib.AuthenticationToken
import br.com.unip.autenticacaolib.dto.DadosToken
import br.com.unip.autenticacaolib.exception.TokenExpiradoException
import br.com.unip.autenticacaolib.exception.TokenInvalidoException
import br.com.unip.autenticacaolib.repository.AutenticacaoRestRepository
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

class TokenUtil(val authConfig: AuthenticationConfig) {

    companion object {
        private var cacheMap: HashMap<String, DadosToken> = HashMap()
    }

    private val TOKEN_TAG = "token"
    private val API_KEY_TAG = "key"

    @Throws(TokenExpiradoException::class, TokenInvalidoException::class)
    fun getAuthentication(request: HttpServletRequest): AuthenticationToken {
        val dadosToken = getAuth(request) ?: getOAuth(request) ?: throw TokenInvalidoException()
        val autorizacoes = this.getAutorizacoes(dadosToken)

        return AuthenticationToken(dadosToken.claims, autorizacoes)
    }

    private fun getAuth(request: HttpServletRequest): DadosToken? {
        val token = request.getHeader(TOKEN_TAG)
        if (token.isNullOrEmpty()) {
            return null
        }

        val dadosToken = getDadosToken(token)
        if (tokenExpirado(dadosToken)) {
            throw TokenExpiradoException()
        }
        return dadosToken
    }

    private fun getOAuth(request: HttpServletRequest): DadosToken? {
        val apiKey = request.getHeader(API_KEY_TAG)
        if (apiKey.isNullOrEmpty()) {
            return null
        }

        var dados = cacheMap[apiKey]
        if (dados == null || tokenExpirado(dados)) {
            dados = buscarNovoToken(apiKey)
            atualizarCache(apiKey, dados)
        }
        return dados
    }

    private fun atualizarCache(apiKey: String, dados: DadosToken) {
        cacheMap.remove(apiKey)
        cacheMap.put(apiKey, dados)
    }

    private fun buscarNovoToken(apiKey: String): DadosToken {
        val token = AutenticacaoRestRepository(authConfig).getTokenPorApikey(apiKey)
        return getDadosToken(token)
    }

    private fun getDadosToken(token: String): DadosToken {
        val splitString = token.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val base64EncodedBody = splitString[1]

        val decoder = Base64.getDecoder()
        val body = String(decoder.decode(base64EncodedBody))

        return ObjectMapper().readValue(body, DadosToken::class.java)
    }

    private fun tokenExpirado(dadosToken: DadosToken): Boolean {
        val time = dadosToken.expiration * 1000
        var dateExpiracao = Timestamp(time).toLocalDateTime()

        return LocalDateTime.now().isAfter(dateExpiracao)
    }

    private fun getAutorizacoes(dadosToken: DadosToken): List<SimpleGrantedAuthority> {
        val scopes = dadosToken.claims.scopes
        return scopes.map { s -> SimpleGrantedAuthority(s) }.toList()
    }
}