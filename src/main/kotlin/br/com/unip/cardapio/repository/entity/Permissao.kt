package br.com.unip.cardapio.repository.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Permissao(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long, val nome: String)