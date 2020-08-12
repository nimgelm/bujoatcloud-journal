package io.nimgelm.bujoatcloud.journal.util

import java.util.*

object DateProcessor {

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

    fun getNextDay(date: Date) : Date {
        val calendar = getCalendarSetupForDate(date)
        calendar.add(Calendar.DAY_OF_MONTH, 1)

        return calendar.time
    }

    fun getFirstDayOfMonth(date: Date, monthModifier: Int = 0) : Date {
        val calendar = getCalendarSetupForDate(date)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.MONTH, monthModifier)

        return calendar.time
    }

    fun getMonthDurationForDate(date: Date) : Int {
        return getCalendarSetupForDate(date).getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun getDayOfMonthForDate(date: Date) : Int {
        val calendar = getCalendarSetupForDate(date)
        return calendar.get(Calendar.DAY_OF_MONTH)
    }
}