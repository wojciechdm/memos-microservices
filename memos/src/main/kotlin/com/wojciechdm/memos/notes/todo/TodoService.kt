package com.wojciechdm.memos.notes.todo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
internal class TodoService {
    @Autowired
    private lateinit var repository: TodoRepository

    internal fun getTodos(): Iterable<TodoDTO> = repository.findAll().map(::TodoDTO)

    internal fun insertTodo(todoDTO: TodoDTO): TodoDTO =
            TodoDTO(
                    repository.save(
                            Todo(
                                    title = todoDTO.title,
                                    message = todoDTO.message,
                                    schedule = todoDTO.schedule,
                                    location = todoDTO.location)))

    internal fun deleteTodo(id: String): Unit = repository.deleteById(id)

    internal fun updateTodo(todoDTO: TodoDTO): TodoDTO {
        var todo = repository.findById(todoDTO.id).get()
        todo.title = todoDTO.title
        todo.message = todoDTO.message
        todo.location = todoDTO.location
        todo.modified = todoDTO.modified
        todo = repository.save(todo)
        return TodoDTO(todo)
    }

    internal fun getScheduledLaterThan(date: Date?): Iterable<TodoDTO> {
        date?.let {
            return repository.findScheduledLaterThan(date.time).map(::TodoDTO)
        }
        return listOf()
    }
}