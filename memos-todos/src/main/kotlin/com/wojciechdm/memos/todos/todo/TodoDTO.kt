package com.wojciechdm.memos.todos.todo

import java.time.LocalDate
import java.time.LocalDateTime

internal data class TodoDTO(
        var id: String = "",
        var title: String,
        var message: String,
        var deadline: LocalDate?,
        var created: LocalDateTime = LocalDateTime.now(),
        var modified: LocalDateTime = LocalDateTime.now()) {

    constructor() : this("", "", "", null)
}