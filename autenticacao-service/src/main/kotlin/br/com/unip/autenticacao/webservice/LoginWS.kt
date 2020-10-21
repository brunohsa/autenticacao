package br.com.unip.autenticacao.webservice

import br.com.unip.autenticacao.dto.firebase.LoginDTO
import br.com.unip.autenticacao.service.ILoginService
import br.com.unip.autenticacao.webservice.model.request.LoginRequest
import br.com.unip.autenticacaolib.repository.AutenticacaoRestRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value = ["/v1/autenticar"])
class LoginWS(val loginService: ILoginService) {

    private val TOKEN_TAG = "token"

    @PostMapping
    fun login(@RequestBody request: LoginRequest, response: HttpServletResponse): ResponseEntity<Void> {
        val dto = LoginDTO(request.email, request.senha)
        val token = loginService.autenticar(dto)

        this.montarHeader(response, token)
        return ResponseEntity.ok().build()
    }

    @GetMapping(value = ["/facebook"])
    fun loginFacebook(@RequestParam("token") tokenFacebook: String, response: HttpServletResponse): ResponseEntity<Void> {
        val token = loginService.autenticarViaFacebook(tokenFacebook)
        this.montarHeader(response, token)

        return ResponseEntity.ok().build()
    }

    @GetMapping(value = ["/oauth"])
    fun oauth(@RequestParam("key") key: String, response: HttpServletResponse): ResponseEntity<Void> {
        val token = loginService.autenticarOAuth(key)
        this.montarHeader(response, token)
        return ResponseEntity.ok().build()
    }

    private fun montarHeader(response: HttpServletResponse, token: String) {
        response.addHeader(TOKEN_TAG, token)
    }
}