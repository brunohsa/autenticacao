package br.com.unip.cardapio.service

import br.com.unip.cardapio.domain.UsuarioDomain
import br.com.unip.cardapio.dto.*
import br.com.unip.cardapio.exception.CampoInvalidoException
import br.com.unip.cardapio.exception.ECodigoErro
import br.com.unip.cardapio.repository.CadastroRepository
import br.com.unip.cardapio.repository.IFirebaseRepository
import br.com.unip.cardapio.repository.IPessoaRepository
import br.com.unip.cardapio.repository.IUsuarioRepository
import br.com.unip.cardapio.repository.entity.enums.EPerfis
import br.com.unip.cardapio.repository.entity.enums.ESituacaoUsuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(val usuarioRepository: IUsuarioRepository,
                     val firebaseService: IFirebaseRepository,
                     val pessoaRepository: IPessoaRepository,
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
            return pessoaRepository.cadastrar(pessoa)
        }
        return pessoaRepository.cadastrar(pessoa as PessoaJuridicaDTO)
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
}