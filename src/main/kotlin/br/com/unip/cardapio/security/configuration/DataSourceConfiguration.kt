package br.com.unip.cardapio.security.configuration

import liquibase.integration.spring.SpringLiquibase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfiguration {

    @Bean
    fun liquibase(): SpringLiquibase {
        val liquibase = SpringLiquibase()
        liquibase.setShouldRun(false)
        return liquibase
    }
}


