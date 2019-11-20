package br.com.unip.cardapio.webservice

import br.com.unip.cardapio.dto.CadastroDTO
import br.com.unip.cardapio.dto.PessoaFisicaDTO
import br.com.unip.cardapio.dto.PessoaJuridicaDTO
import br.com.unip.cardapio.dto.UsuarioDTO
import br.com.unip.cardapio.service.IUsuarioService
import br.com.unip.cardapio.webservice.model.request.UsuarioPFRequest
import br.com.unip.cardapio.webservice.model.request.UsuarioPJRequest
import br.com.unip.cardapio.webservice.model.response.CadastroResponse
import br.com.unip.cardapio.webservice.model.response.PessoaResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/usuarios"])
@EnableGlobalMethodSecurity(prePostEnabled = true)
class UsuarioWS(val usuarioService: IUsuarioService) {

    @RequestMapping(value = ["/cadastrar/cliente"], method = [RequestMethod.POST])
    fun criarUsuario(@RequestBody request: UsuarioPFRequest): ResponseEntity<String> {
        val pessoa = PessoaFisicaDTO(request.nome!!,
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

    @RequestMapping(value = ["/{email}/cadastro"], method = [RequestMethod.GET])
    fun buscarCadastro(@PathVariable("email") email: String): ResponseEntity<CadastroResponse> {
        val cadastro = usuarioService.buscarCadastro(email)
        return ResponseEntity.ok(map(cadastro))
    }

    private fun map(cadastroDTO: CadastroDTO): CadastroResponse {
        val pessoaDTO = cadastroDTO.pessoa
        val pessoaResponse = PessoaResponse(pessoaDTO.nome, pessoaDTO.tipoDocumento, pessoaDTO.numero)

        return CadastroResponse(cadastroDTO.uuid, cadastroDTO.status, pessoaResponse)
    }
}