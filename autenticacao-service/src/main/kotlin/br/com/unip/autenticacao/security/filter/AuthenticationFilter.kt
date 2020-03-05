package br.com.unip.autenticacao.security.filter


import br.com.unip.autenticacao.exception.ECodigoErro
import br.com.unip.autenticacao.exception.TokenExpiradoException
import br.com.unip.autenticacao.exception.TokenInvalidoException
import br.com.unip.autenticacao.webservice.model.response.erro.EMicroservice
import br.com.unip.autenticacao.webservice.model.response.erro.Erro
import br.com.unip.autenticacao.webservice.model.response.erro.ResponseError
import br.com.unip.autenticacaolib.util.TokenUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.util.Locale
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(val messageSource: MessageSource) : GenericFilterBean() {

    private val PT = "pt"
    private val BR = "BR"

    @Throws(TokenExpiradoException::class, TokenInvalidoException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        try {
            val authentication = TokenUtil().getAuthentication(request as HttpServletRequest)
            SecurityContextHolder.getContext().authentication = authentication
            filterChain.doFilter(request, response)
        } catch (e: TokenExpiradoException) {
            setJWTErrorResponse(response, e.httpStatus, e.codigoErro)
        } catch (e: TokenInvalidoException) {
            setJWTErrorResponse(response, e.httpStatus, e.codigoErro)
        }
    }

    @Throws(IOException::class)
    private fun setJWTErrorResponse(response: ServletResponse, httpStatus: HttpStatus, codigo: ECodigoErro) {
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