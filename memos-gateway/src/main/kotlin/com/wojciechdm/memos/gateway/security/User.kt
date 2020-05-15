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
        open var id: String = "",
        @Column(unique = true, nullable = false)
        @NotNull
        open var name: String = "",
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotBlank
        open var pwd: String = "",
        open var roles: String = "",
        open var enabled: Boolean = true,
        open var accountNonExpired: Boolean = true,
        open var accountNonLocked: Boolean = true,
        open var credentialsNonExpired: Boolean = true,
        @CreationTimestamp
        open var created: LocalDateTime = LocalDateTime.now(),
        @UpdateTimestamp
        open var modified: LocalDateTime = LocalDateTime.now()
) : UserDetails {

    constructor() : this("")

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