package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.dto.DadosAutenticacaoDTO
import br.com.unip.autenticacao.dto.firebase.LoginDTO

interface ILoginService {

    fun autenticar(dto : LoginDTO) : DadosAutenticacaoDTO

    fun autenticarOAuth(apikey : String) : DadosAutenticacaoDTO

    fun autenticarViaFacebook(token: String) : DadosAutenticacaoDTO
}