package com.wojciechdm.memos.gateway.security

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class UserConfiguration {

    @Bean
    internal fun modelMapper(): ModelMapper = ModelMapper()
}