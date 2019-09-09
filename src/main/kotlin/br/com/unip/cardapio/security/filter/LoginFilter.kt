package br.com.unip.cardapio.security.filter

import br.com.confidencecambio.auth.dto.LoginDTO
import br.com.unip.cardapio.security.dto.AuthenticatedUser
import br.com.unip.cardapio.security.dto.DatabaseUsernamePasswordAuthentication
import br.com.unip.cardapio.util.TokenUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class LoginFilter : AbstractAuthenticationProcessingFilter {

    private val authManager: AuthenticationManager
    private val tokenUtil: TokenUtil

    constructor(authManager: AuthenticationManager,
                tokenUtil: TokenUtil) : super("/auth") {
        this.authManager = authManager
        this.tokenUtil = tokenUtil
    }

    @Throws(IOException::class)
    override fun attemptAuthentication(httpServletRequest: HttpServletRequest,
                                       httpServletResponse: HttpServletResponse): Authentication {
        val loginDTO = ObjectMapper().readValue(httpServletRequest.inputStream, LoginDTO::class.java)
        return authManager.authenticate(getAuthentication(loginDTO))
    }

    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          chain: FilterChain?,
                                          authResult: Authentication) {
        val token = tokenUtil.gerarToken(authResult.principal as AuthenticatedUser)
        response.addHeader("token", token)
    }

    @Throws(IOException::class)
    override fun unsuccessfulAuthentication(request: HttpServletRequest,
                                            response: HttpServletResponse,
                                            failed: AuthenticationException) {
        val payload = ObjectMapper().writeValueAsString("erro ao fazer login")
        response.writer.write(payload)
        response.contentType = "application/json"
        response.status = HttpServletResponse.SC_FORBIDDEN
    }

    private fun getAuthentication(login: LoginDTO): Authentication {
        return DatabaseUsernamePasswordAuthentication(login.usuario, login.senha)
    }
}
