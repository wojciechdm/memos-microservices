package com.wojciechdm.memos.gateway.security

import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    internal fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("12345")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("12345")
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