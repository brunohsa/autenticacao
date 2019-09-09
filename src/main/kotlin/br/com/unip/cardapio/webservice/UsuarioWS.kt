package br.com.unip.cardapio.webservice

import br.com.unip.cardapio.dto.UsuarioDTO
import br.com.unip.cardapio.service.IUsuarioService
import br.com.unip.cardapio.webservice.model.request.UsuarioRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuarios")
class UsuarioWS(val usuarioService: IUsuarioService) {

    @RequestMapping(method = [RequestMethod.POST])
    fun criarUsuario(@RequestBody request: UsuarioRequest): ResponseEntity<String> {

        val dto = UsuarioDTO(request.username, request.password, request.uuidPessoa, true)
        usuarioService.criarUsuario(dto)

        return ResponseEntity.ok().build()
    }
}