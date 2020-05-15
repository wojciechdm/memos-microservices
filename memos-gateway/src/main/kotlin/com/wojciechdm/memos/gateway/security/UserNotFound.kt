package com.wojciechdm.memos.gateway.security

internal class UserNotFound(override val message: String) : RuntimeException(message)