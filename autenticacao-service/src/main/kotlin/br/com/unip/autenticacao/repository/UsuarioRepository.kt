package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.domain.UsuarioDomain
import br.com.unip.autenticacao.dto.PermissaoDTO
import br.com.unip.autenticacao.dto.UsuarioDTO
import br.com.unip.autenticacao.repository.entity.Usuario
import br.com.unip.autenticacao.repository.entity.enums.ESituacaoUsuario
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UsuarioRepository(val em: EntityManager, val perfilRepository: IPerfilRepository) : IUsuarioRepository {

    @Transactional
    override fun criar(domain: UsuarioDomain) {
        val entity = toEntity(domain, null)
        em.persist(entity)
    }

    @Transactional
    override fun criar(domain: UsuarioDomain, cadastroUuid: String) {
        val entity = toEntity(domain, cadastroUuid)
        em.persist(entity)
    }

    private fun toEntity(domain: UsuarioDomain, cadastroUuid: String?): Usuario {
        val perfis = perfilRepository.buscarPorNomes(domain.perfis)
        return Usuario(
                email = domain.email.get(),
                uuid = UUID.randomUUID().toString(),
                cadastroUuid = cadastroUuid,
                situacao = domain.situacao,
                perfis = perfis
        )
    }

    override fun buscar(email: String): UsuarioDTO? {
        var sql = StringBuilder()
                .append("SELECT new ${UsuarioDTO::class.qualifiedName}(u.id, u.uuid, u.email) ")
                .append("FROM Usuario u ")
                .append("WHERE u.email =:email ")
                .append("AND u.situacao in (:situacoes) ")
                .toString()

        var query = em.createQuery(sql)
        query.setParameter("email", email)
        query.setParameter("situacoes", listOf(ESituacaoUsuario.ATIVO, ESituacaoUsuario.PENDENTE_CADASTRO))

        return try {
            query.singleResult as UsuarioDTO
        } catch (ex: Throwable) {
            null
        }
    }

    override fun buscarPermissoes(id: Long): List<PermissaoDTO> {
        val sql = StringBuilder()
                .append("SELECT new ${PermissaoDTO::class.qualifiedName}(p.id, p.nome) ")
                .append("FROM Usuario u ")
                .append("JOIN u.perfis perfis ")
                .append("JOIN perfis.permissoes p ")
                .append("WHERE u.id =:id")
                .toString()

        val query = em.createQuery(sql)
        query.setParameter("id", id)

        return query.resultList as List<PermissaoDTO>
    }

    override fun buscarPermissoes(email: String): List<PermissaoDTO> {
        val sql = StringBuilder()
                .append("SELECT new ${PermissaoDTO::class.qualifiedName}(p.id, p.nome) ")
                .append("FROM Usuario u ")
                .append("JOIN u.perfis perfis ")
                .append("JOIN perfis.permissoes p ")
                .append("WHERE u.email =:email")
                .toString()

        val query = em.createQuery(sql)
        query.setParameter("email", email)

        return query.resultList as List<PermissaoDTO>
    }

    override fun usuarioCadastrado(email: String): Boolean {
        var sql = StringBuilder()
                .append("SELECT count(u) ")
                .append("FROM ${Usuario::class.qualifiedName} u ")
                .append("WHERE u.email =:email ")
                .toString()

        var query = em.createQuery(sql)
        query.setParameter("email", email)

        return query.singleResult as Long > 0
    }

    override fun buscarPorEmail(email: String): UsuarioDTO {
        var sql = StringBuilder()
                .append("SELECT new ${UsuarioDTO::class.qualifiedName}(u.uuid, u.email, u.cadastroUuid) ")
                .append("FROM ${Usuario::class.qualifiedName} u ")
                .append("WHERE u.email =:email ")
                .toString()

        var query = em.createQuery(sql, UsuarioDTO::class.java)
        query.setParameter("email", email)

        return query.singleResult
    }

    override fun buscarPorApiKey(apikey: String): UsuarioDTO {
        var sql = StringBuilder()
                .append("SELECT new ${UsuarioDTO::class.qualifiedName}(u.id, u.uuid, u.email, u.senha) ")
                .append("FROM ${Usuario::class.qualifiedName} u ")
                .append("WHERE u.apikey =:apikey ")
                .toString()

        var query = em.createQuery(sql, UsuarioDTO::class.java)
        query.setParameter("apikey", apikey)

        return query.singleResult
    }
}