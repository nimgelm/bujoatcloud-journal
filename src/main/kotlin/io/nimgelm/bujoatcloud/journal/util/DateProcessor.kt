package io.nimgelm.bujoatcloud.journal.util

import java.util.*

object DateProcessor {

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