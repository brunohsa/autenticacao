package br.com.unip.autenticacao.repository

import br.com.unip.autenticacao.repository.entity.Perfil
import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import org.springframework.stereotype.Repository

@Repository
class PerfilRepository(val em: EntityManager) : IPerfilRepository {

    override fun buscarPorNome(nome: String): Perfil? {
        val sql = StringBuilder()
                .append("SELECT p FROM ${Perfil::class.qualifiedName} p")
                .append("WHERE p.nome =:nome ")
                .toString()

        val query = em.createQuery(sql)
        query.setParameter("nome", nome)

        return try {
            query.singleResult as Perfil
        } catch (ex: NoResultException) {
            null
        }
    }

    override fun buscarPorNomes(nomes: List<String>): List<Perfil> {
        val sql = StringBuilder()
                .append("SELECT p FROM ${Perfil::class.qualifiedName} p ")
                .append("WHERE p.nome in (:nomes) ")
                .toString()

        val query = em.createQuery(sql)
        query.setParameter("nomes", nomes)

        return query.resultList as List<Perfil>
    }
}