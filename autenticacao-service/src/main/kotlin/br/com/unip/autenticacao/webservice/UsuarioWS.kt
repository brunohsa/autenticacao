package br.com.unip.autenticacao.webservice

import br.com.unip.autenticacao.dto.CadastroDTO
import br.com.unip.autenticacao.dto.DocumentoDTO
import br.com.unip.autenticacao.dto.PessoaFisicaDTO
import br.com.unip.autenticacao.dto.PessoaJuridicaDTO
import br.com.unip.autenticacao.dto.UsuarioDTO
import br.com.unip.autenticacao.service.IUsuarioService
import br.com.unip.autenticacao.webservice.model.request.UsuarioPFRequest
import br.com.unip.autenticacao.webservice.model.request.UsuarioPJRequest
import br.com.unip.autenticacao.webservice.model.response.CadastroResponse
import br.com.unip.autenticacao.webservice.model.response.DocumentoResponse
import br.com.unip.autenticacao.webservice.model.response.PessoaResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/usuarios"])
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
        val pj = PessoaJuridicaDTO(request.razaoSocial, request.nomeFantasia, request.cnpj, request.telefone)
        val dto = UsuarioDTO(request.email, request.senha, pj)
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
        val documentoDTO = pessoaDTO.documento

        val pessoaResponse = PessoaResponse(pessoaDTO.nome, map(documentoDTO))
        return CadastroResponse(cadastroDTO.uuid, cadastroDTO.status, pessoaResponse)
    }

    private fun map(documentoDTO: DocumentoDTO?): DocumentoResponse? {
        if (documentoDTO == null) {
            return null
        }
        return DocumentoResponse(documentoDTO.tipo, documentoDTO.numero)
    }
}