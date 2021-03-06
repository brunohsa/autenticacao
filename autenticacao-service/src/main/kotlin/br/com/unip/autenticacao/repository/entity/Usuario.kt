package br.com.unip.autenticacao.repository.entity

import br.com.unip.autenticacao.repository.entity.enums.ESituacaoUsuario
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
class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @Column(unique = true)
    val email: String

    @Column
    var senha: String = ""

    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Usuario_Perfis",
            joinColumns = [JoinColumn(name = "usuario_id")],
            inverseJoinColumns = [JoinColumn(name = "perfil_id")])
    var perfis: List<Perfil> = emptyList()

    @Column(unique = true)
    val uuid: String

    @Column(name = "cadastro_uuid")
    val cadastroUuid: String?

    @Column
    @Enumerated(EnumType.STRING)
    val situacao: ESituacaoUsuario

    @Column
    var apikey: String = ""

    constructor(email: String, uuid: String, cadastroUuid: String?, situacao: ESituacaoUsuario, perfis: List<Perfil>) {
        this.email = email
        this.uuid = uuid
        this.cadastroUuid = cadastroUuid
        this.situacao = situacao
        this.perfis = perfis
    }
}
