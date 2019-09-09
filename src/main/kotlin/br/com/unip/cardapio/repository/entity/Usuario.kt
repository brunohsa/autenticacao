package br.com.unip.cardapio.repository.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity
class Usuario(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
              val username: String,
              val password: String,
              @ManyToMany(fetch = FetchType.LAZY)
              @JoinTable(name = "usuario_perfis",
                      joinColumns = [JoinColumn(name = "usuario_id")],
                      inverseJoinColumns = [JoinColumn(name = "perfil_id")]) val perfis: List<Perfil> = emptyList(),
              val uuid: String,
              val pessoaId: Long,
              val ativo: Boolean)