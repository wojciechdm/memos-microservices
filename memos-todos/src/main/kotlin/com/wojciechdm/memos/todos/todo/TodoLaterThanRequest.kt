package com.wojciechdm.memos.todos.todo

import java.time.LocalDate

internal data class TodoLaterThanRequest(internal val date: LocalDate? = null) {
    internal constructor() : this(null)
}