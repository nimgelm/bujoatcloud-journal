package io.nimgelm.bujoatcloud.journal.util

import java.time.LocalTime
import java.time.ZonedDateTime
import java.util.*

object DateProcessor {

    fun getTodaysDateAtMidnight(zonedDateTime: ZonedDateTime) : ZonedDateTime {
        return zonedDateTime.with(LocalTime.MIDNIGHT)
    }

    fun getDayOfTheYearAsString(date: Date) : String {
        val calendar = getCalendarSetupForDate(date)
        return "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH)}-${calendar.get(Calendar.DAY_OF_MONTH)}"
    }

    fun getWeekOfTheYearAsString(date: Date) : String {
        val calendar = getCalendarSetupForDate(date)
        return "Week #${calendar.get(Calendar.WEEK_OF_YEAR)} - ${calendar.weekYear}"
    }

    fun getMonthOfTheYearAsString(date: Date) : String {
        val calendar = getCalendarSetupForDate(date)
        return "Month #${calendar.get(Calendar.MONTH)} - ${calendar.weekYear}"
    }

    private fun getCalendarSetupForDate(date: Date) : Calendar {
        val calendar = GregorianCalendar()
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.minimalDaysInFirstWeek = 4
        calendar.timeZone = TimeZone.getDefault()
        calendar.time = date

        return calendar
    }
}