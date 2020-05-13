package com.wojciechdm.memos.notes.note

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
internal class NoteService {
    @Autowired
    private lateinit var repository: NoteRepository

    @Autowired
    private lateinit var mapper: ModelMapper

    internal fun getNotes(): List<NoteDTO> = repository.findAll().map {
        mapper.map(it, NoteDTO::class.java)
    }

    internal fun insertNote(noteDTO: NoteDTO): NoteDTO =
            mapper.map(repository.save(
                    mapper.map(noteDTO, Note::class.java)), NoteDTO::class.java)

    internal fun deleteNote(id: String): Unit = repository.deleteById(id)

    internal fun updateNote(noteDTO: NoteDTO): NoteDTO {
        var note = repository.findById(noteDTO.id).get()
        note.title = noteDTO.title
        note.message = noteDTO.message
        note.modified = noteDTO.modified
        note = repository.save(note)
        return mapper.map(note, NoteDTO::class.java)
    }

    internal fun findByTitle(title: String): List<NoteDTO> =
            repository.findByTitle(title).map {
                mapper.map(it, NoteDTO::class.java)
            }
}