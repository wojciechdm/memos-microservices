package com.wojciechdm.memos.gateway.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.vote.AuthenticatedVoter
import org.springframework.security.access.vote.RoleVoter
import org.springframework.security.access.vote.UnanimousBased
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.expression.WebExpressionVoter
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import java.util.*

@Configuration
@EnableWebSecurity
@Order(1)
internal class SecurityConfig(
        private val service: UserService,
        private val unauthorizedHandler: AuthenticationEntryPoint,
        private val successHandler: WebSecurityAuthSuccessHandler,
        private val encoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {

    @Bean
    internal fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(service)
        authProvider.setPasswordEncoder(encoder)
        return authProvider
    }

    @Bean
    internal fun accessDecisionManager(): AccessDecisionManager {
        val decisionVoters = Arrays.asList(
                WebExpressionVoter(),
                RoleVoter(),
                AuthenticatedVoter()
        )
        return UnanimousBased(decisionVoters)
    }

    @Autowired
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider())
    }

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/notes").authenticated()
                .antMatchers("/notes/**").authenticated()
                .antMatchers("/todos").authenticated()
                .antMatchers("/todos/**").authenticated()
                .antMatchers("/users").hasAnyAuthority("ADMIN")
                .antMatchers("/users/**").hasAnyAuthority("ADMIN")
                .antMatchers("/eureka/**").hasAnyAuthority("ADMIN")
                .and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout()
    }
}