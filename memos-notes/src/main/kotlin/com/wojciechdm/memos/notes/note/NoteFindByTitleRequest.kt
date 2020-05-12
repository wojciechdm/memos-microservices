package com.wojciechdm.memos.notes.note

internal data class NoteFindByTitleRequest(internal val title: String) {
    internal constructor() : this("")
}