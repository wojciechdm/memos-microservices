package com.wojciechdm.memos.gateway.security

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
internal open class User(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(unique = true, nullable = false)
        internal var id: String = "",
        @Column(unique = true, nullable = false)
        @NotNull
        internal var name: String = "",
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotBlank
        internal var pwd: String = "",
        internal var roles: String = "",
        internal var enabled: Boolean = true,
        internal var accountNonExpired: Boolean = true,
        internal var accountNonLocked: Boolean = true,
        internal var credentialsNonExpired: Boolean = true,
        @CreationTimestamp
        internal var created: LocalDateTime = LocalDateTime.now(),
        @UpdateTimestamp
        internal var modified: LocalDateTime = LocalDateTime.now()
) : UserDetails {

    internal constructor() : this("")

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        roles.split(",")
                .forEach { authorities.add(SimpleGrantedAuthority(it.trim())) }
        return authorities
    }

    override fun isEnabled(): Boolean = enabled

    override fun getUsername(): String = name

    override fun isCredentialsNonExpired(): Boolean = credentialsNonExpired

    override fun getPassword(): String = pwd

    override fun isAccountNonExpired(): Boolean = accountNonExpired

    override fun isAccountNonLocked(): Boolean = accountNonLocked
}