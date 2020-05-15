package com.wojciechdm.memos.gateway.security

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
internal class UserConfiguration {

    @Bean
    internal fun modelMapper(): ModelMapper = ModelMapper()

    @Bean
    internal fun encoder(): PasswordEncoder = BCryptPasswordEncoder(11)
}