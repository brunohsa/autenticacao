package br.com.unip.autenticacao.webservice.model.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class UsuarioPJRequest(@JsonProperty("email") val email: String?,
                       @JsonProperty("senha") val senha: String?,
                       @JsonProperty("razao_social") val razaoSocial: String?,
                       @JsonProperty("nome_fantasia") val nomeFantasia: String?,
                       @JsonProperty("cnpj") val cnpj: String?,
                       @JsonProperty("telefone") val telefone: String?)