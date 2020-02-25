package br.com.unip.autenticacaolib.exception

import br.com.unip.autenticacaolib.dto.Erro
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class HandlerException {

    @ExceptionHandler(BaseException::class)
    fun autenticacaoException(ex: BaseException): ResponseEntity<Any> {
        val erro = Erro(ex.codigoErro!!.codigo, ex.codigoErro!!.codigo)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro)
    }
}