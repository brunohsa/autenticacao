package br.com.unip.cardapio.service

import kotlin.reflect.KClass

interface IRestService {

    fun <T : Any> post(uri: String, request: Any, response: KClass<T>): T

    fun post(uri: String, request: Any): String
}