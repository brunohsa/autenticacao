package br.com.unip.cardapio.repository

import br.com.unip.cardapio.UsuarioDomain
import br.com.unip.cardapio.dto.PermissaoDTO
import br.com.unip.cardapio.dto.UsuarioDTO
import br.com.unip.cardapio.repository.entity.Usuario
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.transaction.Transactional

@Repository
class UsuarioRepository(val em: EntityManager) : IUsuarioRepository {

    @Transactional
    override fun criar(domain: UsuarioDomain) {
        val entity = toEntity(domain)
        em.persist(entity)
    }

    private fun toEntity(domain: UsuarioDomain): Usuario {
        return Usuario(username = domain.username,
                password = BCryptPasswordEncoder().encode(domain.password),
                uuid = UUID.randomUUID().toString(),
                pessoaId = 1L,
                ativo = domain.ativo
        )
    }

    override fun buscar(username: String): UsuarioDTO? {
        var sql = StringBuilder()
                .append("SELECT new ${UsuarioDTO::class.qualifiedName}(u.id, u.username, u.password, u.ativo) ")
                .append("FROM Usuario u ")
                .append("WHERE u.username =:username ")
                .append("AND u.ativo =:ativo ")
                .toString()

        var query = em.createQuery(sql)
        query.setParameter("username", username)
        query.setParameter("ativo", true)

        return try {
            query.singleResult as UsuarioDTO
        } catch (ex: NoResultException) {
            null
        }
    }

    override fun buscarPermissoes(id: Long): List<PermissaoDTO> {
        var sql = StringBuilder()
                .append("SELECT new ${PermissaoDTO::class.qualifiedName}(p.id, p.nome) ")
                .append("FROM Usuario u ")
                .append("JOIN u.perfis perfis ")
                .append("JOIN perfis.permissoes p ")
                .append("WHERE u.id =:id")
                .toString()

        var query = em.createQuery(sql)
        query.setParameter("id", id)

        return query.resultList as List<PermissaoDTO>
    }
}