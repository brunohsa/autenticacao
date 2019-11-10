package br.com.unip.cardapio.repository.entity

import br.com.unip.cardapio.repository.entity.enums.ESituacaoUsuario
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity
class Usuario(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
              @Column(unique = true) val email: String,
              @Column @ManyToMany(fetch = FetchType.LAZY)
              @JoinTable(name = "Usuario_Perfis",
                      joinColumns = [JoinColumn(name = "usuario_id")],
                      inverseJoinColumns = [JoinColumn(name = "perfil_id")]) val perfis: List<Perfil> = emptyList(),
              @Column(unique = true) val uuid: String,
              @Column(name = "cadastro_uuid") val cadastroUuid: String?,
              @Column @Enumerated(EnumType.STRING) val situacao: ESituacaoUsuario)
