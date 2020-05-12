package com.wojciechdm.memos.gateway.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
internal class UserController {

    @Autowired
    internal lateinit var service: UserService

    @GetMapping
    internal fun getUsers(): Collection<UserDetailsDTO> = service.getUsers()

    @PutMapping("/admin")
    internal fun insertAdmin(@RequestBody user: UserDTO): UserDetailsDTO = service.saveAdmin(user)

    @PutMapping("/member")
    internal fun insertMember(@RequestBody user: UserDTO): UserDetailsDTO = service.saveMember(user)

    @DeleteMapping("/{id}")
    internal fun deleteUser(@PathVariable(name = "id") id: String): Unit = service.deleteUser(id)

    @PostMapping
    internal fun updateUser(@RequestBody user: User): UserDetailsDTO? = service.updateUser(user)
}