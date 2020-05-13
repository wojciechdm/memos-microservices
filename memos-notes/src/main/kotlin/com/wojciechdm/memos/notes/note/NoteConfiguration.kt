package com.wojciechdm.memos.notes.note

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class NoteConfiguration {

    @Bean
    internal fun modelMapper(): ModelMapper = ModelMapper()
}