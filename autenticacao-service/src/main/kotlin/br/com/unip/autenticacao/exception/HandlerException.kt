package br.com.unip.autenticacao.exception

import br.com.unip.autenticacao.dto.FirebaseErrorDTO
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
        System.out.println(e.message)
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
        val codigoErroFirebase = getErrosFirebase(e.responseBodyAsString)
        val erro = if (codigoErroFirebase != null) {
            ResponseError(getErro(codigoErroFirebase))
        } else {
            mapper.readValue<ResponseError>(e.responseBodyAsString, ResponseError::class.java)
        }
        return ResponseEntity.status(e.statusCode).body(erro)
    }


    private fun getErrosFirebase(erro: String): ECodigoErro? {
        val erroFirebase = mapper.readValue<FirebaseErrorDTO>(erro, FirebaseErrorDTO::class.java)
        val mensagemErro = erroFirebase.error!!.message!!

        return if (mensagemErro.contains(ECodigoErro.INVALID_PASSWORD.name)) {
            ECodigoErro.INVALID_PASSWORD
        } else if (mensagemErro.contains(ECodigoErro.TOO_MANY_ATTEMPTS_TRY_LATER.name)) {
            ECodigoErro.TOO_MANY_ATTEMPTS_TRY_LATER
        } else {
            null
        }
    }

    private fun getErro(erro: ECodigoErro): Erro {
        return Erro(erro.codigo, getMensagem(erro))
    }

    private fun getMensagem(codigoErro: ECodigoErro): String {
        val local = Locale(PT, BR)
        return messageSource.getMessage(codigoErro.codigo, null, local)
    }
}