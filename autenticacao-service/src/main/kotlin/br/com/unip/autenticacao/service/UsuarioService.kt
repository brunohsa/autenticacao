package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.domain.UsuarioDomain
import br.com.unip.autenticacao.dto.CadastroDTO
import br.com.unip.autenticacao.dto.IPessoaDTO
import br.com.unip.autenticacao.dto.PermissaoDTO
import br.com.unip.autenticacao.dto.PessoaFisicaDTO
import br.com.unip.autenticacao.dto.PessoaJuridicaDTO
import br.com.unip.autenticacao.dto.UsuarioDTO
import br.com.unip.autenticacao.exception.CampoInvalidoException
import br.com.unip.autenticacao.exception.ECodigoErro
import br.com.unip.autenticacao.repository.CadastroRepository
import br.com.unip.autenticacao.repository.IFirebaseRepository
import br.com.unip.autenticacao.repository.IUsuarioRepository
import br.com.unip.autenticacao.repository.entity.enums.EPerfis
import br.com.unip.autenticacao.repository.entity.enums.ESituacaoUsuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(val usuarioRepository: IUsuarioRepository,
                     val firebaseService: IFirebaseRepository,
                     val cadastroRepository: CadastroRepository) : IUsuarioService {

    override fun cadastrarConsumidor(usuario: UsuarioDTO) {
        val perfis = EPerfis.perfisConsumidor()
        this.cadastrarUsuario(usuario, perfis)
    }

    override fun cadastrarFornecedor(usuario: UsuarioDTO) {
        val perfis = EPerfis.perfisFornecedor()
        this.cadastrarUsuario(usuario, perfis)
    }

    override fun cadastrarUsuarioOAuth(usuario: UsuarioDTO) {
        val perfis = EPerfis.perfisConsumidor()
        val domain = mapDomain(usuario, ESituacaoUsuario.ATIVO, perfis)
        usuarioRepository.criar(domain)
    }

    private fun cadastrarUsuario(usuario: UsuarioDTO, perfis: List<String>) {
        val usuarioDomain = mapDomain(usuario, ESituacaoUsuario.ATIVO, perfis)
        if (usuarioRepository.usuarioCadastrado(usuario.email!!)) {
            throw CampoInvalidoException(ECodigoErro.USUARIO_JA_CADASTRADO)
        }
        val uuid: String = this.cadastrarPessoa(usuario.pessoa!!)

        firebaseService.cadastrarUsuario(usuarioDomain)
        usuarioRepository.criar(usuarioDomain, uuid)
    }

    private fun mapDomain(usuario: UsuarioDTO, situacao: ESituacaoUsuario, perfis: List<String>): UsuarioDomain {
        return UsuarioDomain(usuario.email, usuario.senha, situacao, perfis)
    }

    private fun cadastrarPessoa(pessoa: IPessoaDTO): String {
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

    override fun buscarUsuario(email: String): UsuarioDTO {
        return usuarioRepository.buscarPorEmail(email)
    }
}