package br.com.unip.autenticacao.security.filter

import br.com.unip.autenticacao.exception.ECodigoErro
import br.com.unip.autenticacao.exception.ECodigoErro.TOKEN_EXPIRADO
import br.com.unip.autenticacao.exception.ECodigoErro.TOKEN_INVALIDO
import br.com.unip.autenticacao.webservice.model.response.erro.Erro
import br.com.unip.autenticacaolib.AuthenticationConfig
import br.com.unip.autenticacaolib.exception.TokenExpiradoException
import br.com.unip.autenticacaolib.exception.TokenInvalidoException
import br.com.unip.autenticacaolib.util.TokenUtil
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.MessageSource
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.util.*

class AuthenticationFilter(val messageSource: MessageSource, val env: Environment) : GenericFilterBean() {

    private val AUTH_URL_PROPERTY = "microservice.autenticacao.url"

    private val PT = "pt"
    private val BR = "BR"

    @Throws(TokenExpiradoException::class, TokenInvalidoException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        try {
            val config = AuthenticationConfig(env.getProperty(AUTH_URL_PROPERTY)!!)
            val authentication = TokenUtil(config).getAuthentication(request as HttpServletRequest)
            SecurityContextHolder.getContext().authentication = authentication
            filterChain.doFilter(request, response)
        } catch (e: TokenExpiradoException) {
            setJWTErrorResponse(response, TOKEN_EXPIRADO, FORBIDDEN)
        } catch (e: TokenInvalidoException) {
            setJWTErrorResponse(response, TOKEN_INVALIDO, UNAUTHORIZED)
        }
    }

    @Throws(IOException::class)
    private fun setJWTErrorResponse(response: ServletResponse, codigo: ECodigoErro, httpStatus: HttpStatus) {
        val httpResponse = response as HttpServletResponse

        val erro = getErro(codigo)
        httpResponse.writer.write(ObjectMapper().writeValueAsString(erro))
        httpResponse.status = httpStatus.value()
        httpResponse.contentType = MediaType.APPLICATION_JSON_VALUE
    }

    private fun getErro(erro: ECodigoErro): Erro {
        return Erro(erro.codigo, getMensagem(erro))
    }

    private fun getMensagem(codigoErro: ECodigoErro): String {
        val local = Locale(PT, BR)
        return messageSource.getMessage(codigoErro.codigo, null, local)
    }
}