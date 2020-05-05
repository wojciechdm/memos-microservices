package com.wojciechdm.memos.note

internal data class NoteFindByTitleRequest(internal val title: String) {
    internal constructor() : this("")
}