package com.wojciechdm.memos.gateway.security

import java.time.LocalDateTime

internal class UserDetailsDTO(
        internal var id: String,
        internal var name: String,
        internal var roles: String,
        internal var enabled: Boolean,
        internal var accountNonExpired: Boolean,
        internal var accountNonLocked: Boolean,
        internal var credentialsNonExpired: Boolean,
        internal var created: LocalDateTime,
        internal var modified: LocalDateTime)