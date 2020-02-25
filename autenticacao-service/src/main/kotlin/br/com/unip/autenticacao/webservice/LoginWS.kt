package br.com.unip.autenticacao.webservice

import br.com.unip.autenticacao.dto.firebase.LoginDTO
import br.com.unip.autenticacao.dto.firebase.OAuthLoginFirebaseDTO
import br.com.unip.autenticacao.service.ILoginService
import br.com.unip.autenticacao.webservice.model.request.LoginRequest
import br.com.unip.autenticacao.webservice.model.request.OAuthLoginRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value = ["/v1/autenticar"])
class LoginWS(val loginService: ILoginService) {

    @Value("\${facebook.provider-id}")
    private val providerFacebook: String? = null

    @Value("\${firebase.request.uri}")
    private val requestUri: String? = null


    @RequestMapping(method = [RequestMethod.POST])
    fun login(@RequestBody request: LoginRequest, response: HttpServletResponse): ResponseEntity<String> {
        val dto = LoginDTO(request.email, request.senha)
        val token = loginService.autenticar(dto)

        this.montarHeader(response, token)

        return ResponseEntity.ok().build()
    }

    @RequestMapping(value = ["/facebook"], method = [RequestMethod.POST])
    fun loginFacebook(@RequestBody request: OAuthLoginRequest, response: HttpServletResponse): ResponseEntity<String> {
        val dto = OAuthLoginFirebaseDTO(request.tokenAcesso, providerFacebook!!, requestUri!!)
        val token = loginService.autenticarViaOAuth(dto)
        this.montarHeader(response, token)

        return ResponseEntity.ok().build()
    }

    private fun montarHeader(response: HttpServletResponse, token: String) {
        response.addHeader("token", token)
    }
}