package io.nimgelm.bujoatcloud.journal.model

import io.nimgelm.bujoatcloud.journal.util.DateProcessor
import org.springframework.data.annotation.Id
import java.util.*
import javax.persistence.*

@Entity
class Month(@OneToMany(mappedBy = "month") var weeks: Week,
            @OneToMany(mappedBy = "month") var retrospectives: Retrospective) {

    @Id
    @GeneratedValue
    var _id: Long? = null

    val name = DateProcessor.getMonthOfTheYearAsString(Date())
}