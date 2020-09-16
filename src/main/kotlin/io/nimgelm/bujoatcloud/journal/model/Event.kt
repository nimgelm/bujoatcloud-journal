package io.nimgelm.bujoatcloud.journal.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class Event(var name: String,
            var description: String?,
            var schedule: ZonedDateTime) {

    @Id
    @GeneratedValue
    var _id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_day")
    private lateinit var day: Day

    fun setDay(newDay: Day) {this.day = newDay}
    fun getDay() : Day = this.day

}