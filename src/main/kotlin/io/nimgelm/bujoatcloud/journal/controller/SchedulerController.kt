package io.nimgelm.bujoatcloud.journal.controller

import io.nimgelm.bujoatcloud.journal.exception.DatabaseSaveException
import io.nimgelm.bujoatcloud.journal.model.*
import io.nimgelm.bujoatcloud.journal.service.DatabaseService
import io.nimgelm.bujoatcloud.journal.util.DateProcessor
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class SchedulerController: ApplicationRunner {

    @Autowired
    lateinit var databaseService: DatabaseService

    private val logger = LoggerFactory.getLogger(SchedulerController::class.java)

    @Scheduled(cron="0 0 5 28 * ?")        //One day at the end of the Month, at 5 a.m.
    fun createDaysForNextMonth() {
        val nextMonthDate = DateProcessor.getFirstDayOfMonth(Date(), 1)
        createDaysForMonthOfDate(nextMonthDate)
    }

    private fun createDaysForMonthOfDate(date: Date) {
        val nextMonthLength = DateProcessor.getMonthDurationForDate(date)
        var nextDayDate = date

        logger.info("Validating days for Month ${DateProcessor.getMonthOfTheYearAsString(date)}")
        for (i in 1..nextMonthLength) {
            getOrCreateDayForDate(nextDayDate)
            nextDayDate = DateProcessor.getNextDay(nextDayDate)
        }
    }

    fun getOrCreateDayForDate(date: Date) : Day {
        val dayName = DateProcessor.getDayOfTheYearAsString(date)
        val existingDay = databaseService.getDayFromName(dayName)

        return if (existingDay == null) {
            logger.info("Creating Day $dayName, since it did not exist.")
            val newDay = Day(ArrayList<Note>(),
                    ArrayList<Event>(),
                    ArrayList<Task>(),
                    getOrCreateWeekForDate(date),
                    dayName)
            try {
                databaseService.saveDay(newDay)
                newDay
            } catch (exception: DatabaseSaveException) {
                throw exception
            }
        } else {
            existingDay
        }
    }

    private fun getOrCreateWeekForDate(date: Date) : Week {
        val weekName = DateProcessor.getWeekOfTheYearAsString(date)
        val existingWeek = databaseService.getWeekForName(weekName)

        return if (existingWeek == null) {
            logger.info("Creating Week $weekName, since it did not exist.")
            val newWeek = Week(ArrayList<Day>(),
                    ArrayList<Objective>(),
                    getOrCreateMonthForDate(date),
                    weekName)
            try {
                databaseService.saveWeek(newWeek)
                newWeek
            } catch (exception: DatabaseSaveException) {
                throw exception
            }
        } else {
            existingWeek
        }
    }

    private fun getOrCreateMonthForDate(date: Date) : Month {
        val monthName = DateProcessor.getMonthOfTheYearAsString(date)
        val existingMonth = databaseService.getMonthForName(monthName)

        return if (existingMonth == null) {
            logger.info("Creating Month $monthName, since it did not exist.")
            val newMonth = Month(ArrayList<Week>(),
                    ArrayList<Retrospective>(),
                    monthName)
            try {
                databaseService.saveMonth(newMonth)
                newMonth
            } catch (exception: DatabaseSaveException) {
                throw exception
            }
        } else {
            existingMonth
        }
    }

    override fun run(args: ApplicationArguments?) {
        logger.info("*** Dates Initiliazion ***")
        val currentMonthDate = DateProcessor.getFirstDayOfMonth(Date())
        createDaysForMonthOfDate(currentMonthDate)
    }

}