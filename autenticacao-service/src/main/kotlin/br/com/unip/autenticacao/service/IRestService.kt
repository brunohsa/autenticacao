package br.com.unip.autenticacao.service

import kotlin.reflect.KClass

interface IRestService {

    fun <T : Any> post(uri: String, request: Any, response: KClass<T>): T

    fun post(uri: String, request: Any): String

    fun <T : Any> get(uri: String, response: KClass<T>): T
}