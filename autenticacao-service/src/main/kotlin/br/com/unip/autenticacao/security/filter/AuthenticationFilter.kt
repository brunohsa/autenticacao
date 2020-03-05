package br.com.unip.autenticacao.security.filter


import br.com.unip.autenticacao.exception.ECodigoErro
import br.com.unip.autenticacao.exception.ECodigoErro.TOKEN_EXPIRADO
import br.com.unip.autenticacao.exception.ECodigoErro.TOKEN_INVALIDO
import br.com.unip.autenticacao.exception.TokenExpiradoException
import br.com.unip.autenticacao.exception.TokenInvalidoException
import br.com.unip.autenticacao.webservice.model.response.ErroResponse
import br.com.unip.autenticacaolib.util.TokenUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter : GenericFilterBean() {

    @Throws(TokenExpiradoException::class, TokenInvalidoException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        try {
            val authentication = TokenUtil().getAuthentication(request as HttpServletRequest)
            SecurityContextHolder.getContext().authentication = authentication
            filterChain.doFilter(request, response)
        } catch (e: TokenExpiradoException) {
            setJWTErrorResponse(response, HttpStatus.BAD_REQUEST, TOKEN_EXPIRADO, "Token expirado")
        } catch (e: TokenInvalidoException) {
            setJWTErrorResponse(response, HttpStatus.UNAUTHORIZED, TOKEN_INVALIDO, "Token Iválido")
        }
    }

    @Throws(IOException::class)
    private fun setJWTErrorResponse(response: ServletResponse, httpStatus: HttpStatus, codigo: ECodigoErro,
                                    mensagem: String) {
        val httpResponse = response as HttpServletResponse

        val error = ErroResponse(codigo.codigo, mensagem)
        httpResponse.writer.write(ObjectMapper().writeValueAsString(error))
        httpResponse.status = httpStatus.value()
        httpResponse.contentType = MediaType.APPLICATION_JSON_VALUE
    }
}