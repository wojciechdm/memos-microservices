package com.wojciechdm.memos.gateway.security

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@DiscriminatorValue(value = "MEMBER")
internal class Member(
        id: String = "",
        name: String = "",
        pwd: String = "",
        roles: String = "",
        enabled: Boolean = true,
        accountNonExpired: Boolean = true,
        accountNonLocked: Boolean = true,
        credentialsNonExpired: Boolean = true,
        created: LocalDateTime = LocalDateTime.now(),
        modified: LocalDateTime = LocalDateTime.now()
) : User(
        id,
        name,
        pwd,
        roles,
        enabled,
        accountNonExpired,
        accountNonLocked,
        credentialsNonExpired,
        created,
        modified
) {
   internal constructor() : this("")
}