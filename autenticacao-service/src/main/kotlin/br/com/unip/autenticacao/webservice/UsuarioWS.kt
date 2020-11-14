package br.com.unip.autenticacao.webservice

import br.com.unip.autenticacao.dto.DadosAutenticacaoDTO
import br.com.unip.autenticacao.dto.PessoaFisicaDTO
import br.com.unip.autenticacao.dto.PessoaJuridicaDTO
import br.com.unip.autenticacao.dto.UsuarioDTO
import br.com.unip.autenticacao.dto.firebase.LoginDTO
import br.com.unip.autenticacao.service.ILoginService
import br.com.unip.autenticacao.service.IUsuarioService
import br.com.unip.autenticacao.webservice.model.request.UsuarioPFRequest
import br.com.unip.autenticacao.webservice.model.request.UsuarioPJRequest
import br.com.unip.autenticacao.webservice.model.response.DadosAutenticacaoResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value = ["/v1/usuarios"])
@EnableGlobalMethodSecurity(prePostEnabled = true)
class UsuarioWS(val usuarioService: IUsuarioService, val loginService: ILoginService) {

    private val TOKEN_TAG = "token"

    @PostMapping(value = ["/cadastrar/cliente"])
    fun criarUsuario(@RequestBody request: UsuarioPFRequest, response: HttpServletResponse): ResponseEntity<DadosAutenticacaoResponse> {
        val pf = PessoaFisicaDTO(request.nome, request.sobrenome, request.telefone, request.dataNascimento, request.cpf)
        val dto = UsuarioDTO(request.email, request.senha, pf)
        usuarioService.cadastrarConsumidor(dto)

        val loginDTO = LoginDTO(request.email!!, request.senha!!)
        val dadosAutenticadoDTO = loginService.autenticar(loginDTO)
        response.addHeader(TOKEN_TAG, dadosAutenticadoDTO.token)

        return ResponseEntity.ok(dadosAutenticadoDTO.toResponse())
    }

    @PostMapping(value = ["/cadastrar/fornecedor"])
    fun criarUsuarioFornecedor(@RequestBody request: UsuarioPJRequest, response: HttpServletResponse): ResponseEntity<DadosAutenticacaoResponse> {
        val pj = PessoaJuridicaDTO(request.razaoSocial, request.nomeFantasia, request.cnpj, request.telefone)
        val dto = UsuarioDTO(request.email, request.senha, pj)
        usuarioService.cadastrarFornecedor(dto)

        val loginDTO = LoginDTO(request.email!!, request.senha!!)
        val dadosAutenticadoDTO = loginService.autenticar(loginDTO)
        response.addHeader(TOKEN_TAG, dadosAutenticadoDTO.token)

        return ResponseEntity.ok(dadosAutenticadoDTO.toResponse())
    }

    private fun DadosAutenticacaoDTO.toResponse() = DadosAutenticacaoResponse(this.email, this.cadastroUUID)
}