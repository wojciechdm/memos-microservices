package com.wojciechdm.memos.todos.todo

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
internal interface TodoRepository : CrudRepository<Todo, String> {

    @Query("FROM Todo WHERE deadline > ?1")
    fun findScheduledLaterThan(date: LocalDate): Iterable<Todo>
}