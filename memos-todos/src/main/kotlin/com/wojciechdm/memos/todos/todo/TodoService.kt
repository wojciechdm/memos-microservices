package com.wojciechdm.memos.todos.todo

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
internal class TodoService(
        private val repository: TodoRepository,
        private val mapper: ModelMapper
) {

    internal fun getTodos(): List<TodoDTO> =
            repository.findAll().map { mapper.map(it, TodoDTO::class.java) }

    internal fun insertTodo(todoDTO: TodoDTO): TodoDTO =
            mapper.map(repository.save(
                    mapper.map(todoDTO, Todo::class.java)), TodoDTO::class.java)

    internal fun deleteTodo(id: String): Unit = repository.deleteById(id)

    internal fun updateTodo(todoDTO: TodoDTO): TodoDTO {
        var todo = repository.findById(todoDTO.id).get()
        todo.title = todoDTO.title
        todo.message = todoDTO.message
        todo.deadline = todoDTO.deadline
        todo.modified = todoDTO.modified
        todo = repository.save(todo)
        return mapper.map(todo, TodoDTO::class.java)
    }

    internal fun getScheduledLaterThan(date: LocalDate?): List<TodoDTO> {
        date?.let {
            return repository.findScheduledLaterThan(date).map {
                mapper.map(it, TodoDTO::class.java)
            }
        }
        return listOf()
    }
}