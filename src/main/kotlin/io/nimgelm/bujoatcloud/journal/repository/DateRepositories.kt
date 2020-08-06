package io.nimgelm.bujoatcloud.journal.repository

import io.nimgelm.bujoatcloud.journal.model.Day
import io.nimgelm.bujoatcloud.journal.model.Month
import io.nimgelm.bujoatcloud.journal.model.Week
import org.springframework.data.repository.CrudRepository

interface DayRepository : CrudRepository<Day, Long> {
}

interface WeekRepository : CrudRepository<Week, Long> {
}

interface MonthRepository : CrudRepository<Month, Long> {
}