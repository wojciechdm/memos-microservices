package com.wojciechdm.memos.notes.note

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "note")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NamedQuery(name = "Note.findByTitle", query = "SELECT note FROM Note note WHERE title LIKE ?1")
internal data class Note(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "varchar(36)")
        var id: String = "",
        var title: String,
        var message: String,
        @CreationTimestamp
        var created: LocalDateTime = LocalDateTime.now(),
        @UpdateTimestamp
        var modified: LocalDateTime = LocalDateTime.now()) {

        constructor() : this("", "", "")
}