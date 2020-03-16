package br.com.unip.autenticacao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class PessoaJuridicaDTO(@JsonProperty("razao_social") val razaoSocial: String?,
                        @JsonProperty("nome_fantasia") val nomeFantasia: String?,
                        @JsonProperty("cnpj") val cnpj: String?,
                        @JsonProperty("telefone") val telefone: String?) : IPessoaDTO