package br.com.unip.cardapio.repository.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Perfil(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
             val nome: String,
             val descricao: String,
             @ManyToMany(fetch = FetchType.LAZY) val permissoes: List<Permissao>) {
}