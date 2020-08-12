package io.nimgelm.bujoatcloud.journal.repository

import io.nimgelm.bujoatcloud.journal.model.Day
import io.nimgelm.bujoatcloud.journal.model.Month
import io.nimgelm.bujoatcloud.journal.model.Week
import org.springframework.data.repository.CrudRepository
import java.time.ZonedDateTime

interface DayRepository : CrudRepository<Day, Long> {

    fun findByToday(today: ZonedDateTime) : Day?

    fun findByName(name: String) : Day?
}

interface WeekRepository : CrudRepository<Week, Long> {

    fun findByName(name: String) : Week?
}

interface MonthRepository : CrudRepository<Month, Long> {

    fun findByName(name: String) : Month?
}