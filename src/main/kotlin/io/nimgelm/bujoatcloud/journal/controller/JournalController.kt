package io.nimgelm.bujoatcloud.journal.controller

import io.nimgelm.bujoatcloud.journal.model.Note
import io.nimgelm.bujoatcloud.journal.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/api/journal")
class JournalController {

    @Autowired lateinit var noteRepository: NoteRepository

    @PostMapping("/v1/Notes")
    fun insertNewNote(@RequestBody(required = true) note: Note) : ResponseEntity<String> {
        val newNote = noteRepository.save(note)
        //ToDo - add validations
        return ResponseEntity("${newNote._id}", HttpStatus.CREATED)
    }

    //TODO - Add other endpoints
    //TODO - Configure Hibernate and connection to MySQL server
}