package com.wojciechdm.memos.todos.security

import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@Order(1)
internal class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .httpBasic().disable().authorizeRequests()
                .antMatchers("/todos").permitAll()
                .antMatchers("/todos/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
    }
}