package io.nimgelm.bujoatcloud.journal.model

import java.util.*
import javax.persistence.*

@Entity
class Task(name: String,
           description: String?,
           @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_day") var day: Day) {

    @Id
    @GeneratedValue
    var _id: Long? = null

    var isCompleted: Boolean = false
    var completionDate : Date? = null
}