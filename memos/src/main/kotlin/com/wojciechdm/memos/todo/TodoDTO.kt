package com.wojciechdm.memos.todo

import java.util.*

data class TodoDTO(
        var id: String = "",
        var title: String,
        var message: String,
        var schedule: Long,
        var location: String = "",
        var created: Date = Date(),
        var modified: Date = Date()) {

    constructor() : this(title = "", message = "", schedule = -1, location = "")

    internal constructor(todo: Todo) :
            this(
                    todo.id,
                    todo.title,
                    todo.message,
                    todo.schedule,
                    todo.location,
                    todo.created,
                    todo.modified)
}