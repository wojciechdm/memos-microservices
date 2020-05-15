package com.wojciechdm.memos.todos.todo

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "todo")
@JsonInclude(JsonInclude.Include.NON_NULL)
internal data class Todo(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "varchar(36)")
        var id: String = "",
        var title: String,
        var message: String,
        var deadline: LocalDate?,
        @CreationTimestamp
        var created: LocalDateTime = LocalDateTime.now(),
        @UpdateTimestamp
        var modified: LocalDateTime = LocalDateTime.now()) {

        constructor() : this("", "", "", null)
}