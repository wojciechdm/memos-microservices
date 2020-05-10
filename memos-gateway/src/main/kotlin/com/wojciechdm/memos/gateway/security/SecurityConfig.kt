package com.wojciechdm.memos.gateway.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
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

    @Value("\${gateway.admin.username:}")
    private lateinit var adminUsername: String

    @Value("\${gateway.admin.password:}")
    private lateinit var adminPassword: String

    @Value("\${gateway.user.username:}")
    private lateinit var userUsername: String

    @Value("\${gateway.user.password:}")
    private lateinit var userPassword: String

    @Autowired
    internal fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .inMemoryAuthentication()
                .withUser(userUsername)
                .password(userPassword)
                .roles("USER")
                .and()
                .withUser(adminUsername)
                .password(adminPassword)
                .roles("ADMIN")
    }

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/memos/**")
                .permitAll()
                .antMatchers("/eureka/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/memos/**").permitAll()
                .and()
                .csrf().disable()
    }
}