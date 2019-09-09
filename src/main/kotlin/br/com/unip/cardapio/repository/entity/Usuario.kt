package br.com.unip.cardapio.repository.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Usuario(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
              val username: String,
              val password: String,
              @ManyToMany(fetch = FetchType.LAZY) val perfis: List<Perfil> = emptyList(),
              val uuid: String,
              val uuidPessoa: String,
              val ativo: Boolean) {

    fun getPermissoes(): Set<Permissao> {
        val permissoes = HashSet<Permissao>()
        if (perfis.isNotEmpty()) {
            perfis.forEach { perfil -> permissoes.addAll(perfil.permissoes) }
        }
        return permissoes
    }
}