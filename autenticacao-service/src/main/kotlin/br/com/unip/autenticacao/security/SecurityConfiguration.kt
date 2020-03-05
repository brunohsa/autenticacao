package br.com.unip.autenticacao.security

import br.com.unip.autenticacao.security.filter.AuthenticationFilter
import br.com.unip.autenticacao.security.filter.CorsFilterCustom
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(val messageSource: MessageSource) : WebSecurityConfigurerAdapter() {

   override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/v1/autenticar",
                                               "/v1/autenticar/oauth",
                                               "/v1/autenticar/facebook",
                                               "/v1/usuarios/cadastrar/cliente",
                                               "/v1/usuarios/cadastrar/fornecedor"
        )
    }

    @Throws(Exception::class)
    public override fun configure(http: HttpSecurity) {
        http.cors()
                .and()
                .csrf().disable()

        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(CorsFilterCustom(), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(AuthenticationFilter(messageSource), UsernamePasswordAuthenticationFilter::class.java)
    }
}