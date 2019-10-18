package br.com.unip.cardapio.webservice

import br.com.unip.cardapio.dto.PessoaFisicaDTO
import br.com.unip.cardapio.dto.PessoaJuridicaDTO
import br.com.unip.cardapio.dto.UsuarioDTO
import br.com.unip.cardapio.service.IUsuarioService
import br.com.unip.cardapio.webservice.model.request.UsuarioPFRequest
import br.com.unip.cardapio.webservice.model.request.UsuarioPJRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/usuarios"])
@EnableGlobalMethodSecurity(prePostEnabled = true)
class UsuarioWS(val usuarioService: IUsuarioService) {

    @RequestMapping(value = ["/cadastrar"], method = [RequestMethod.POST])
    fun criarUsuario(@RequestBody request: UsuarioPFRequest): ResponseEntity<String> {
        val pessoa = PessoaFisicaDTO(request.nome,
                request.sobrenome,
                request.telefone,
                request.dataNascimento,
                request.cpf)
        val dto = UsuarioDTO(request.email, request.senha, pessoa)
        usuarioService.cadastrarConsumidor(dto)

        return ResponseEntity.ok().build()
    }

    @RequestMapping(value = ["/cadastrar/fornecedor"], method = [RequestMethod.POST])
    fun criarUsuarioFornecedor(@RequestBody request: UsuarioPJRequest): ResponseEntity<String> {
        val pessoa = PessoaJuridicaDTO(request.nome, request.telefone, request.dataFundacao, request.cnpj)
        val dto = UsuarioDTO(request.email, request.senha, pessoa)
        usuarioService.cadastrarFornecedor(dto)

        return ResponseEntity.ok().build()
    }
}