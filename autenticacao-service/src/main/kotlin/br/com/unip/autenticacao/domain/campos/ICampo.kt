package br.com.unip.autenticacao.domain.campos

interface ICampo<T> {

    fun get(): T
}