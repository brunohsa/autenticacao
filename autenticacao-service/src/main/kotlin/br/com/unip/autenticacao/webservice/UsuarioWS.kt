package br.com.unip.autenticacao.webservice

import br.com.unip.autenticacao.dto.CadastroDTO
import br.com.unip.autenticacao.dto.PessoaFisicaDTO
import br.com.unip.autenticacao.dto.PessoaJuridicaDTO
import br.com.unip.autenticacao.dto.UsuarioDTO
import br.com.unip.autenticacao.service.IUsuarioService
import br.com.unip.autenticacao.webservice.model.request.UsuarioPFRequest
import br.com.unip.autenticacao.webservice.model.request.UsuarioPJRequest
import br.com.unip.autenticacao.webservice.model.response.CadastroResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/usuarios"])
@EnableGlobalMethodSecurity(prePostEnabled = true)
class UsuarioWS(val usuarioService: IUsuarioService) {

    @PostMapping(value = ["/cadastrar/cliente"])
    fun criarUsuario(@RequestBody request: UsuarioPFRequest): ResponseEntity<String> {
        val pf = PessoaFisicaDTO(request.nome, request.sobrenome, request.telefone, request.dataNascimento, request.cpf)
        val dto = UsuarioDTO(request.email, request.senha, pf)
        usuarioService.cadastrarConsumidor(dto)

        return ResponseEntity.ok().build()
    }

    @PostMapping(value = ["/cadastrar/fornecedor"])
    fun criarUsuarioFornecedor(@RequestBody request: UsuarioPJRequest): ResponseEntity<String> {
        val pj = PessoaJuridicaDTO(request.razaoSocial, request.nomeFantasia, request.cnpj, request.telefone)
        val dto = UsuarioDTO(request.email, request.senha, pj)
        usuarioService.cadastrarFornecedor(dto)

        return ResponseEntity.ok().build()
    }

    @GetMapping(value = ["/{email}/cadastro"])
    fun buscarCadastro(@PathVariable("email") email: String): ResponseEntity<CadastroResponse> {
        val cadastro = usuarioService.buscarCadastro(email)
        return ResponseEntity.ok(map(cadastro))
    }

    private fun map(cadastroDTO: CadastroDTO): CadastroResponse {
        return CadastroResponse(cadastroDTO.uuid, cadastroDTO.status)
    }
}