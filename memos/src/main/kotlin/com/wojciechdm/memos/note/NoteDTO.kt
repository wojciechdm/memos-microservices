package com.wojciechdm.memos.note

import java.util.*

data class NoteDTO(
        var id: String = "",
        var title: String,
        var message: String,
        var location: String = "",
        var created: Date = Date(),
        var modified: Date = Date()) {

    constructor() : this(title = "", message = "", location = "")

    internal constructor(note: Note) :
            this(
                    note.id,
                    note.title,
                    note.message,
                    note.location,
                    note.created,
                    note.modified)
}