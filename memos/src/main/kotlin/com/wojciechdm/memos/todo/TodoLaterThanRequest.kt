package com.wojciechdm.memos.todo

import java.util.*

internal data class TodoLaterThanRequest(internal val date: Date? = null) {
    internal constructor() : this(null)
}