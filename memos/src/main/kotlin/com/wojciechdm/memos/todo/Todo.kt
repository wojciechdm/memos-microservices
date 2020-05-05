package com.wojciechdm.memos.todo

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "todo")
@JsonInclude(JsonInclude.Include.NON_NULL)
internal data class Todo(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "varchar(36)")
        internal var id: String = "",
        internal var title: String,
        internal var message: String,
        internal var schedule: Long,
        internal var location: String = "",
        @CreationTimestamp
        internal var created: Date = Date(),
        @UpdateTimestamp
        internal var modified: Date = Date()) {

        internal constructor() : this("", "", "", -1, "")
}