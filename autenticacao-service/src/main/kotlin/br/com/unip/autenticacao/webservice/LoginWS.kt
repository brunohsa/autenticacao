package br.com.unip.autenticacao.webservice

import br.com.unip.autenticacao.dto.firebase.LoginDTO
import br.com.unip.autenticacao.service.ILoginService
import br.com.unip.autenticacao.webservice.model.request.LoginRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/v1/autenticar"])
class LoginWS(val loginService: ILoginService) {

    private val TOKEN_TAG = "token"

    @PostMapping
    fun login(@RequestBody request: LoginRequest, response: HttpServletResponse): ResponseEntity<String> {
        val dto = LoginDTO(request.email, request.senha)
        val token = loginService.autenticar(dto)

        this.montarHeader(response, token)
        return ResponseEntity.ok().build()
    }

    @GetMapping(value = ["/oauth"])
    fun oauth(@RequestParam("key") key: String, response: HttpServletResponse): ResponseEntity<String> {
        val token = loginService.autenticarOAuth(key)
        this.montarHeader(response, token)
        return ResponseEntity.ok().build()
    }

    private fun montarHeader(response: HttpServletResponse, token: String) {
        response.addHeader(TOKEN_TAG, token)
    }
}