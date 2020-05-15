package com.wojciechdm.memos.notes.note

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notes")
@EnableAutoConfiguration
internal class NoteController(private val service: NoteService) {

    @GetMapping
    internal fun getNotes(): List<NoteDTO> = service.getNotes()

    @PutMapping
    internal fun insertNote(@RequestBody note: NoteDTO): NoteDTO = service.insertNote(note)

    @DeleteMapping("/{id}")
    internal fun deleteNote(@PathVariable(name = "id") id: String): Unit = service.deleteNote(id)

    @PostMapping
    internal fun updateNote(@RequestBody note: NoteDTO): NoteDTO = service.updateNote(note)

    @PostMapping("/by_title")
    internal fun getNotesByTitle(@RequestBody payload: NoteFindByTitleRequest): List<NoteDTO> =
            service.findByTitle(payload.title)
}