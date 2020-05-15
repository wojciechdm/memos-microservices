package com.wojciechdm.memos.todos.todo

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
@EnableAutoConfiguration
internal class TodoController(private val service: TodoService) {

    @GetMapping
    internal fun getTodos(): List<TodoDTO> = service.getTodos()

    @PutMapping
    internal fun insertTodo(@RequestBody todo: TodoDTO): TodoDTO = service.insertTodo(todo)

    @DeleteMapping("/{id}")
    internal fun deleteTodo(@PathVariable(name = "id") id: String): Unit = service.deleteTodo(id)

    @PostMapping
    internal fun updateTodo(@RequestBody todo: TodoDTO): TodoDTO = service.updateTodo(todo)

    @PostMapping("/later_than")
    internal fun getTodoLaterThan(@RequestBody payload: TodoLaterThanRequest): List<TodoDTO> =
            service.getScheduledLaterThan(payload.date)
}