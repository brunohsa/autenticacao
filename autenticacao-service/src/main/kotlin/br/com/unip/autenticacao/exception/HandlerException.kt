package br.com.unip.autenticacao.exception

import br.com.unip.autenticacao.exception.ECodigoErro.ACESSO_NEGADO
import br.com.unip.autenticacao.exception.ECodigoErro.ERRO_INESPERADO
import br.com.unip.autenticacao.webservice.model.response.erro.Erro
import br.com.unip.autenticacao.webservice.model.response.erro.ResponseError
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException
import java.util.Locale

@ControllerAdvice
class HandlerException(val mapper: ObjectMapper, val messageSource: MessageSource) {

    private val PT = "pt"
    private val BR = "BR"

    @ExceptionHandler(Throwable::class)
    fun handlerErroInesperado(e: Throwable): ResponseEntity<ResponseError> {
        val erro = getErro(ERRO_INESPERADO)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseError(erro))
    }

    @ExceptionHandler(AccessDeniedException::class, AuthenticationCredentialsNotFoundException::class)
    fun handlerAcessoNegado(e: Exception): ResponseEntity<ResponseError> {
        val erro = getErro(ACESSO_NEGADO)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseError(erro))
    }

    @ExceptionHandler(AutenticacaoBaseException::class)
    fun handlerErroAutenticacao(e: AutenticacaoBaseException): ResponseEntity<ResponseError> {
        val erro = getErro(e.codigoErro)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseError(erro))
    }

    @ExceptionHandler(HttpClientErrorException::class)
    fun handlerErroIntegracao(e: HttpClientErrorException): ResponseEntity<Any> {
        val erro = mapper.readValue<ResponseError>(e.responseBodyAsString, ResponseError::class.java)
        return ResponseEntity.status(e.statusCode).body(erro)
    }

    private fun getErro(erro: ECodigoErro): Erro {
        return Erro(erro.codigo, getMensagem(erro))
    }

    private fun getMensagem(codigoErro: ECodigoErro): String {
        val local = Locale(PT, BR)
        return messageSource.getMessage(codigoErro.codigo, null, local)
    }
}