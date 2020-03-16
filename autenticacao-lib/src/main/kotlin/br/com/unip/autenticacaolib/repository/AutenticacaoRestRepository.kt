package br.com.unip.autenticacaolib.repository

import br.com.unip.autenticacaolib.AuthenticationConfig
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.GET
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class AutenticacaoRestRepository(val authConfig: AuthenticationConfig) {

    private val AUTENTICAR_PATH = "v1/autenticar"
    private val TOKEN_PARAM = "token"
    private val API_KEY_PARAM = "key"

    fun getTokenPorApikey(apikey: String): String {
        val map = LinkedMultiValueMap<String, String>()
        map.add(API_KEY_PARAM, apikey)

        val url = authConfig.urlAutenticacao
        val response = get("$url$AUTENTICAR_PATH/oauth", map)
        return response.headers.getFirst(TOKEN_PARAM)!!
    }

    private fun get(url: String, params: MultiValueMap<String, String>): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(null, headers)

        val uri = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build().toUri()
        return RestTemplate().exchange(uri, GET, entity, String::class.java)
    }
}