package io.nimgelm.bujoatcloud.journal.model

import io.nimgelm.bujoatcloud.journal.util.DateProcessor
import java.util.*
import javax.persistence.*

@Entity
class Month(@OneToMany(mappedBy = "month") var weeks: List<Week>,
            @OneToMany(mappedBy = "month") var retrospectives: List<Retrospective>,
            val name: String = DateProcessor.getMonthOfTheYearAsString(Date())) {

    @Id
    @GeneratedValue
    var _id: Long? = null


}