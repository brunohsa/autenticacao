package br.com.unip.autenticacao.repository.entity

import jakarta.persistence.*

@Entity
class Permissao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
                @Column(unique = true) val nome: String,
                @Column val descricao: String)