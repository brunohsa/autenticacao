package br.com.unip.cardapio.service

import br.com.unip.cardapio.UsuarioDomain
import br.com.unip.cardapio.dto.UsuarioDTO
import br.com.unip.cardapio.repository.IUsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(val usuarioRepository: IUsuarioRepository) : IUsuarioService {

    override fun criarUsuario(usuario: UsuarioDTO) {
        val domain = UsuarioDomain(usuario.username, usuario.password, usuario.uuidPessoa, usuario.ativo)
        usuarioRepository.criar(domain)
    }
}