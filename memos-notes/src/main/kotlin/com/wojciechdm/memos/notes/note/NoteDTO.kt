package com.wojciechdm.memos.notes.note

import java.time.LocalDateTime

internal data class NoteDTO(
        var id: String = "",
        var title: String,
        var message: String,
        var created: LocalDateTime = LocalDateTime.now(),
        var modified: LocalDateTime = LocalDateTime.now()) {

    constructor() : this(title = "", message = "")
}