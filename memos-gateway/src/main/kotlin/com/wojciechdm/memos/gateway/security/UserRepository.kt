package com.wojciechdm.memos.gateway.security

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
internal interface UserRepository : CrudRepository<User, String> {
    fun findOneByName(name: String): User?
}