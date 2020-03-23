package br.com.unip.autenticacao.configuration

import br.com.unip.autenticacao.util.FirebaseUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:application.properties")
class FirebaseConfiguration {

    val firebaseUtil: FirebaseUtil

    constructor(firebaseUtil: FirebaseUtil) {
        this.firebaseUtil = firebaseUtil
    }

    @Bean
    fun inicializarApp() {
        firebaseUtil.inicializarApp()
    }
}


