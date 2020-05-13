package com.wojciechdm.memos.gateway.security

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime

@Service
internal class UserService : UserDetailsService {

    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    private lateinit var mapper: ModelMapper

    private val encoder = BCryptPasswordEncoder(11)

    override fun loadUserByUsername(username: String): User? =
            repository.findOneByName(username) ?: throw RuntimeException("$username doesn't exists")

    internal fun saveAdmin(user: UserDTO): UserDetailsDTO {
        val admin = Admin()
        admin.name = user.name
        admin.pwd = encoder.encode(user.pwd)
        admin.roles = "ADMIN, MEMBER"
        return mapper.map(repository.save(admin), UserDetailsDTO::class.java)
    }

    internal fun saveMember(user: UserDTO): UserDetailsDTO {
        val member = Member()
        member.name = user.name
        member.pwd = encoder.encode(user.pwd)
        member.roles = "MEMBER"
        return mapper.map(repository.save(member), UserDetailsDTO::class.java)
    }

    internal fun updateUser(user: UserDTO): UserDetailsDTO? {
        val toUpdate = repository.findOneByName(user.name)
        toUpdate?.let {
            if (user.pwd.isNotEmpty()) {
                toUpdate.pwd = encoder.encode(user.pwd)
            }
            toUpdate.modified = LocalDateTime.now()
            return mapper.map(repository.save(toUpdate), UserDetailsDTO::class.java)
        }
        return null
    }

    internal fun getUsers(): List<UserDetailsDTO> = repository.findAll().map {
        mapper.map(it, UserDetailsDTO::class.java)
    }

    internal fun deleteUser(id: String): Unit = repository.deleteById(id)
}