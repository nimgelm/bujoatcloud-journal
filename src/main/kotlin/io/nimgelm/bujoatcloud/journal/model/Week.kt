package io.nimgelm.bujoatcloud.journal.model

import io.nimgelm.bujoatcloud.journal.util.DateProcessor
import java.util.*
import javax.persistence.*

@Entity
class Week(@OneToMany(mappedBy = "week") var days: List<Day>,
           @OneToMany(mappedBy = "week") var objectives: List<Objective>,
           @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_month") var month: Month,
           val name: String = DateProcessor.getWeekOfTheYearAsString(Date())) {

    @Id
    @GeneratedValue
    var _id: Long? = null
}