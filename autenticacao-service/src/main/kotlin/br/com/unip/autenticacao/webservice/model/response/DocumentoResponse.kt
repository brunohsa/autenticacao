package br.com.unip.autenticacao.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class DocumentoResponse(@JsonProperty(value = "tipo") var tipo: String,
                        @JsonProperty(value = "numero") var numero: String)