package com.wojciechdm.memos.note

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
internal interface NoteRepository : CrudRepository<Note, String> {
    fun findByTitle(title: String): Iterable<Note>
}