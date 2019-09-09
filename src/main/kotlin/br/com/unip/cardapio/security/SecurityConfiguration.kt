package br.com.unip.cardapio.security

import br.com.unip.cardapio.security.filter.CorsFilterCustom
import br.com.unip.cardapio.security.filter.LoginFilter
import br.com.unip.cardapio.security.provider.DatabaseAuthenticationProvider
import br.com.unip.cardapio.service.IDatabaseAuthenticationService
import br.com.unip.cardapio.util.TokenUtil
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(val tokenUtil: TokenUtil,
                            val databaseAuthenticationService: IDatabaseAuthenticationService) :
        WebSecurityConfigurerAdapter() {

    public override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(DatabaseAuthenticationProvider(databaseAuthenticationService))
    }

    @Throws(Exception::class)
    public override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "auth").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "usuario").permitAll().and()
                .addFilterBefore(CorsFilterCustom(), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(LoginFilter(authenticationManager(), tokenUtil),
                        UsernamePasswordAuthenticationFilter::class.java)
    }
}