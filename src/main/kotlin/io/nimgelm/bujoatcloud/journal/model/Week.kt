package io.nimgelm.bujoatcloud.journal.model

import io.nimgelm.bujoatcloud.journal.util.DateProcessor
import org.springframework.data.annotation.Id
import java.util.*
import javax.persistence.*

@Entity
class Week(@OneToMany(mappedBy = "week") var days: Day,
           @OneToMany(mappedBy = "week") var objectives: Objective?,
           @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_month") var month: Month) {

    @Id
    @GeneratedValue
    var _id: Long? = null

    val name = DateProcessor.getWeekOfTheYearAsString(Date())
}