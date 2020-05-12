package com.wojciechdm.memos.notes.note

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
internal class NoteService {
    @Autowired
    private lateinit var repository: NoteRepository

    internal fun getNotes(): Iterable<NoteDTO> = repository.findAll().map(::NoteDTO)

    internal fun insertNote(noteDTO: NoteDTO): NoteDTO =
            NoteDTO(
                    repository.save(
                            Note(
                                    title = noteDTO.title,
                                    message = noteDTO.message,
                                    location = noteDTO.location)))

    internal fun deleteNote(id: String): Unit = repository.deleteById(id)

    internal fun updateNote(noteDTO: NoteDTO): NoteDTO {
        var note = repository.findById(noteDTO.id).get()
        note.title = noteDTO.title
        note.message = noteDTO.message
        note.location = noteDTO.location
        note.modified = noteDTO.modified
        note = repository.save(note)
        return NoteDTO(note)
    }
    internal fun findByTitle(title: String): Iterable<NoteDTO> = repository.findByTitle(title).map(::NoteDTO)
}