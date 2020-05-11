package com.wojciechdm.memos.gateway.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime

@Service
internal class UserService : UserDetailsService {

    @Autowired
    internal lateinit var repository: UserRepository

    private val encoder = BCryptPasswordEncoder(11)

    override fun loadUserByUsername(username: String): User? =
            repository.findOneByName(username) ?: throw RuntimeException("$username doesn't exists")

    internal fun saveAdmin(user: UserDTO): UserDetailsDTO {
        val admin = Admin()
        admin.name = user.name
        admin.pwd = encoder.encode(user.pwd)
        admin.roles = "ADMIN, MEMBER"
        return with(repository.save(admin)) {
            UserDetailsDTO(
                    id,
                    name,
                    roles,
                    enabled,
                    accountNonExpired,
                    accountNonLocked,
                    credentialsNonExpired,
                    created,
                    modified)
        }
    }

    internal fun saveMember(user: UserDTO): UserDetailsDTO {
        val member = Member()
        member.name = user.name
        member.pwd = encoder.encode(user.pwd)
        member.roles = "MEMBER"
        return with(repository.save(member)) {
            UserDetailsDTO(
                    id,
                    name,
                    roles,
                    enabled,
                    accountNonExpired,
                    accountNonLocked,
                    credentialsNonExpired,
                    created,
                    modified)
        }
    }

    internal fun updateUser(user: User): UserDetailsDTO? {
        val toUpdate = repository.findOneByName(user.name)
        toUpdate?.let {
            if (user.pwd.isNotEmpty()) {
                toUpdate.pwd = encoder.encode(user.pwd)
            }
            toUpdate.accountNonExpired = user.accountNonExpired
            toUpdate.accountNonLocked = user.accountNonLocked
            toUpdate.credentialsNonExpired = user.credentialsNonExpired
            toUpdate.modified = LocalDateTime.now()
            return with(repository.save(toUpdate)) {
                UserDetailsDTO(
                        id,
                        name,
                        roles,
                        enabled,
                        accountNonExpired,
                        accountNonLocked,
                        credentialsNonExpired,
                        created,
                        modified)
            }
        }
        return null
    }

    internal fun getUsers(): Collection<UserDetailsDTO> = repository.findAll().map {
        UserDetailsDTO(
                it.id,
                it.name,
                it.roles,
                it.enabled,
                it.accountNonExpired,
                it.accountNonLocked,
                it.credentialsNonExpired,
                it.created,
                it.modified)
    }

    internal fun deleteUser(id: String): Unit = repository.deleteById(id)
}