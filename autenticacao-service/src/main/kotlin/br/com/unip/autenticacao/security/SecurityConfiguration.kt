package br.com.unip.autenticacao.security

import br.com.unip.autenticacao.security.filter.AuthenticationFilter
import br.com.unip.autenticacao.security.filter.CorsFilterCustom
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(val messageSource: MessageSource, val env: Environment) {

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer? {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(
                "/v1/autenticar",
                "/v1/autenticar/oauth",
                "/v1/usuarios/cadastrar/cliente",
                "/v1/usuarios/cadastrar/fornecedor"
            )
        }
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors()
            .and()
            .csrf().disable()

        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(CorsFilterCustom(), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(AuthenticationFilter(messageSource, env), UsernamePasswordAuthenticationFilter::class.java)

        return http.build();
    }
}