package br.com.unip.cardapio.exception

import br.com.unip.cardapio.webservice.model.response.ErroResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class HandlerException {

    @ExceptionHandler(CampoComFormatoInvalidoException::class)
    fun formatoInvalidoException(ex: CampoComFormatoInvalidoException): ResponseEntity<Any> {
        val erro = ErroResponse(ex.codigoErro!!.codigo, ex.codigoErro!!.codigo)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro)
    }

    @ExceptionHandler(CampoObrigatorioNaoInformadoException::class)
    fun campoObrigatorioException(ex: CampoObrigatorioNaoInformadoException): ResponseEntity<Any> {
        val erro = ErroResponse(ex.codigoErro!!.codigo, ex.codigoErro!!.codigo)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro)
    }

    @ExceptionHandler(TokenExpiradoException::class)
    fun tokenExpiradoException(ex: TokenExpiradoException): ResponseEntity<Any> {
        val erro = ErroResponse(ex.codigoErro!!.codigo, ex.codigoErro!!.codigo)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro)
    }

    @ExceptionHandler(CardapBaseException::class)
    fun autenticacaoException(ex: CardapBaseException): ResponseEntity<Any> {
        val erro = ErroResponse(ex.codigoErro!!.codigo, ex.codigoErro!!.codigo)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro)
    }
}