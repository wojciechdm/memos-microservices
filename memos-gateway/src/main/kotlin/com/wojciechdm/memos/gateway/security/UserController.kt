package com.wojciechdm.memos.gateway.security

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
internal class UserController(private val service: UserService) {

    @GetMapping
    internal fun getUsers(): List<UserDetailsDTO> = service.getUsers()

    @PutMapping("/admin")
    internal fun insertAdmin(@RequestBody user: UserDTO): UserDetailsDTO = service.saveAdmin(user)

    @PutMapping("/member")
    internal fun insertMember(@RequestBody user: UserDTO): UserDetailsDTO = service.saveMember(user)

    @DeleteMapping("/{id}")
    internal fun deleteUser(@PathVariable(name = "id") id: String): Unit = service.deleteUser(id)

    @PostMapping
    internal fun updateUser(@RequestBody user: UserDTO): UserDetailsDTO? = service.updateUser(user)
}