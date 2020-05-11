package com.wojciechdm.memos.discovery.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
@Order(1)
internal class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${discovery.username:}")
    private lateinit var username: String
    @Value("\${discovery.password:}")
    private lateinit var password: String

    @Autowired
    internal fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .inMemoryAuthentication()
                .withUser(username)
                .password("{noop}${password}")
                .roles("SYSTEM")
    }

    override fun configure(http: HttpSecurity) {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().requestMatchers().antMatchers("/eureka/**")
                .and().authorizeRequests().antMatchers("/eureka/**")
                .hasRole("SYSTEM").anyRequest().denyAll().and()
                .httpBasic().and().csrf().disable()
    }
}