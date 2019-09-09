package br.com.unip.cardapio.service

import br.com.unip.cardapio.repository.IUsuarioRepository
import br.com.unip.cardapio.dto.PermissaoDTO
import br.com.unip.cardapio.dto.UsuarioDTO
import br.com.unip.cardapio.security.dto.AuthenticatedUser
import br.com.unip.cardapio.security.dto.DatabaseUsernamePasswordAuthentication
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class DatabaseAuthenticationService(val usuarioRepository: IUsuarioRepository) : IDatabaseAuthenticationService {

    override fun authenticate(user: DatabaseUsernamePasswordAuthentication): AuthenticatedUser {
        val usuario = usuarioRepository.buscar(user.name)
        if (autenticacaoValida(user, usuario)) {
            val permissoes = usuarioRepository.buscarPermissoes(usuario!!.id!!)
            return AuthenticatedUser(usuario!!.id!!, usuario.username, usuario.password, mapRoles(permissoes))
        }
        throw BadCredentialsException("Invalid username or password")
    }

    private fun autenticacaoValida(userDetails: DatabaseUsernamePasswordAuthentication,
                                   usuario: UsuarioDTO?): Boolean {
        return usuario != null && BCryptPasswordEncoder().matches(userDetails.credentials as String, usuario.password)
    }

    private fun mapRoles(permissoes: List<PermissaoDTO>): Set<SimpleGrantedAuthority> {
        return permissoes.map { permissao -> SimpleGrantedAuthority(permissao.nome) }.toSet()
    }
}