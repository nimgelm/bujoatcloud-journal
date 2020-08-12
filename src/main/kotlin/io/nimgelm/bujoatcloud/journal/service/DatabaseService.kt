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
import java.util.*

@Service
class DatabaseService {

    @Autowired
    lateinit var eventRepository: EventRepository
    @Autowired
    lateinit var dayRepository: DayRepository
    @Autowired
    lateinit var weekRepository: WeekRepository
    @Autowired
    lateinit var monthRepository: MonthRepository


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
        val todayName = DateProcessor.getDayOfTheYearAsString(Date())
        val today = dayRepository.findByName(todayName)

        if (today == null) {
            throw DatabaseEntryNotFoundException(
                    ExceptionStrings.databaseEntryNotFound("Day", todayName))
        } else {
            return today
        }
    }

    fun getDayFromName(name: String) : Day? {
        return dayRepository.findByName(name)
    }


/********************************
*********** WEEK REPO ***********
*********************************/

    fun saveWeek(week: Week) : Week {
        try {
            return weekRepository.save(week)
        } catch (e: RuntimeException) {
            throw DatabaseSaveException(
                    ExceptionStrings.databaseFailedSave("Week", week.name, e.message))
        }
    }

    fun getWeekForName(name: String) : Week? {
        return weekRepository.findByName(name)
    }


/*********************************
*********** MONTH REPO ***********
**********************************/

    fun saveMonth(month: Month) : Month {
        try {
            return monthRepository.save(month)
        } catch (e: RuntimeException) {
            throw DatabaseSaveException(
                    ExceptionStrings.databaseFailedSave("Month", month.name, e.message))
        }
    }

    fun getMonthForName(name: String) : Month? {
        return monthRepository.findByName(name)
    }

}