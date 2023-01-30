package br.com.unip.autenticacao.repository.entity

import jakarta.persistence.*

@Entity
class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(unique = true)
    lateinit var nome: String

    @Column
    lateinit var descricao: String

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "Perfil_Permissoes",
        joinColumns = [JoinColumn(name = "perfil_id")],
        inverseJoinColumns = [JoinColumn(name = "permissao_id")]
    )
    lateinit var permissoes: List<Permissao>

    constructor()

    constructor(id: Long, nome: String, descricao: String, permissoes: List<Permissao>) {
        this.id = id
        this.nome = nome
        this.descricao = descricao
        this.permissoes = permissoes
    }
}