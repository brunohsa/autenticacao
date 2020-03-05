package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.domain.UsuarioDomain
import br.com.unip.autenticacao.dto.CadastroDTO
import br.com.unip.autenticacao.dto.IPessoaDTO
import br.com.unip.autenticacao.dto.PermissaoDTO
import br.com.unip.autenticacao.dto.PessoaFisicaDTO
import br.com.unip.autenticacao.dto.PessoaJuridicaDTO
import br.com.unip.autenticacao.dto.UsuarioDTO
import br.com.unip.autenticacao.exception.CampoInvalidoException
import br.com.unip.autenticacao.exception.CampoObrigatorioNaoInformadoException
import br.com.unip.autenticacao.exception.ECodigoErro.DADOS_DA_PESSOA_SAO_OBRIGATORIOS
import br.com.unip.autenticacao.exception.ECodigoErro.USUARIO_JA_CADASTRADO
import br.com.unip.autenticacao.repository.CadastroRepository
import br.com.unip.autenticacao.repository.IFirebaseRepository
import br.com.unip.autenticacao.repository.IUsuarioRepository
import br.com.unip.autenticacao.repository.entity.enums.EPerfis
import br.com.unip.autenticacao.repository.entity.enums.ESituacaoUsuario.ATIVO
import org.springframework.stereotype.Service

@Service
class UsuarioService(val usuarioRepository: IUsuarioRepository,
                     val firebaseService: IFirebaseRepository,
                     val cadastroRepository: CadastroRepository) : IUsuarioService {

    override fun cadastrarConsumidor(usuario: UsuarioDTO) {
        val perfis = EPerfis.perfisConsumidor()
        cadastrarUsuario(usuario, perfis, true)
    }

    override fun cadastrarFornecedor(usuario: UsuarioDTO) {
        val perfis = EPerfis.perfisFornecedor()
        cadastrarUsuario(usuario, perfis, true)
    }

    override fun cadastrarUsuarioOAuth(usuario: UsuarioDTO) {
        val perfis = EPerfis.perfisConsumidor()
        cadastrarUsuario(usuario, perfis, false)
    }

    private fun cadastrarUsuario(usuario: UsuarioDTO, perfis: List<String>, cadastrarNoFirebase: Boolean) {
        val usuarioDomain = UsuarioDomain(usuario.email, usuario.senha, ATIVO, perfis)
        validarSeUsuarioJaEstaCadastrado(usuarioDomain.email.get())

        val uuid: String = this.cadastrarPessoa(usuario.pessoa)
        usuarioRepository.criar(usuarioDomain, uuid)

        if (cadastrarNoFirebase) {
            firebaseService.cadastrarUsuario(usuarioDomain)
        }
    }

    private fun validarSeUsuarioJaEstaCadastrado(email: String) {
        if (usuarioRepository.usuarioCadastrado(email)) {
            throw CampoInvalidoException(USUARIO_JA_CADASTRADO)
        }
    }

    private fun cadastrarPessoa(pessoa: IPessoaDTO?): String {
        if (pessoa == null) {
            throw CampoObrigatorioNaoInformadoException(DADOS_DA_PESSOA_SAO_OBRIGATORIOS)
        }
        if (pessoa is PessoaFisicaDTO) {
            return cadastroRepository.cadastrar(pessoa)
        }
        return cadastroRepository.cadastrar(pessoa as PessoaJuridicaDTO)
    }

    override fun buscarPermissoes(email: String): List<PermissaoDTO> {
        return usuarioRepository.buscarPermissoes(email)
    }

    override fun usuarioCadastrado(email: String): Boolean {
        return usuarioRepository.usuarioCadastrado(email)
    }

    override fun buscarCadastro(email: String): CadastroDTO {
        val usuario = usuarioRepository.buscarPorEmail(email)
        return cadastroRepository.buscar(usuario.cadastroUUID!!)
    }

    override fun buscar(email: String): UsuarioDTO {
        return usuarioRepository.buscarPorEmail(email)
    }

    override fun buscarPorApiKey(apiKey: String): UsuarioDTO {
        return usuarioRepository.buscarPorApiKey(apiKey)
    }
}