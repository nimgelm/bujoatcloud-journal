package io.nimgelm.bujoatcloud.journal.model

import org.springframework.data.annotation.Id
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class Event(var name: String,
            var description: String?,
            var schedule: ZonedDateTime,
            @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_day") var day: Day) {

    @Id
    @GeneratedValue
    var _id: Long? = null
}