package br.com.unip.cardapio.webservice.model.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class UsuarioPFRequest(@JsonProperty("email") val email: String,
                       @JsonProperty("senha") val senha: String,
                       @JsonProperty("nome") val nome: String,
                       @JsonProperty("sobrenome") val sobrenome: String,
                       @JsonProperty("telefone") val telefone: String,
                       @JsonProperty("data_nascimento") val dataNascimento: String,
                       @JsonProperty("cpf") val cpf: String)