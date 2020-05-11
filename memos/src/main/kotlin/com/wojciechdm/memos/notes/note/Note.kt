package com.wojciechdm.memos.notes.note

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
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
        internal var id: String = "",
        internal var title: String,
        internal var message: String,
        internal var location: String = "",
        @CreationTimestamp
        internal var created: Date = Date(),
        @UpdateTimestamp
        internal var modified: Date = Date()) {

        internal constructor() : this("", "", "", "")
}