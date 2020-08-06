package io.nimgelm.bujoatcloud.journal.repository

import io.nimgelm.bujoatcloud.journal.model.*
import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<Event, Long> {
}

interface NoteRepository : CrudRepository<Note, Long> {
}

interface ObjectiveRepository : CrudRepository<Objective, Long> {
}

interface RetrospectiveRepository : CrudRepository<Retrospective, Long> {
}

interface TaskRepository : CrudRepository<Task, Long> {
}
