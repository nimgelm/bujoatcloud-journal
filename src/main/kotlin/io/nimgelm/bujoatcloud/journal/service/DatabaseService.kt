package io.nimgelm.bujoatcloud.journal.service

import io.nimgelm.bujoatcloud.journal.exception.DatabaseEmptyListResultException
import io.nimgelm.bujoatcloud.journal.exception.DatabaseEntryNotFoundException
import io.nimgelm.bujoatcloud.journal.exception.DatabaseSaveException
import io.nimgelm.bujoatcloud.journal.exception.ExceptionStrings
import io.nimgelm.bujoatcloud.journal.model.*
import io.nimgelm.bujoatcloud.journal.repository.*
import io.nimgelm.bujoatcloud.journal.util.DateProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.ZonedDateTime

@Service
class DatabaseService {

    @Autowired
    lateinit var eventRepository: EventRepository
    @Autowired
    lateinit var dayRepository: DayRepository


 /*********************************
 *********** EVENT REPO ***********
 **********************************/
    fun saveEvent(event: Event) : Event {
        try {
            return eventRepository.save(event)
        } catch (exception: RuntimeException) {
            throw DatabaseSaveException(
                    ExceptionStrings.databaseFailedSave("Event", event.name, exception.message))
        }
    }

    fun getAllEventsForToday() : List<Event> {
        val today = getDayForToday()
        val events= eventRepository.findByDay(today)

        if (events.isEmpty()) {
            throw DatabaseEmptyListResultException(
                    ExceptionStrings.databaseEmptyListResult("Events", "today"))
        }

        return events
    }


/*******************************
*********** DAY REPO ***********
********************************/
    fun saveDay(day: Day) : Day {
        try {
            return dayRepository.save(day)
        } catch (e: RuntimeException) {
            throw DatabaseSaveException(
                    ExceptionStrings.databaseFailedSave("Day", day.name, e.message))
        }
    }

    fun getDayForToday() : Day {
        val todayAtMidnight = DateProcessor.getTodaysDateAtMidnight(ZonedDateTime.now())
        val today = dayRepository.findByToday(todayAtMidnight)

        if (today == null) {
            throw DatabaseEntryNotFoundException(
                    ExceptionStrings.databaseEntryNotFound("Day", todayAtMidnight.toString()))
        } else {
            return today
        }
    }
}