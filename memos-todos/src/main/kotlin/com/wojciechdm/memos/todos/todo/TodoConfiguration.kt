package com.wojciechdm.memos.todos.todo

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class TodoConfiguration {

    @Bean
    internal fun modelMapper(): ModelMapper = ModelMapper()
}