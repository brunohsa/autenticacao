package br.com.unip.autenticacao.repository.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Permissao(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
                @Column(unique = true) val nome: String,
                @Column val descricao: String)