package io.nimgelm.bujoatcloud.journal.model

import io.nimgelm.bujoatcloud.journal.util.DateProcessor
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.*

@Entity
class Day(@OneToMany(mappedBy = "day") var notes: List<Note>,
          @OneToMany(mappedBy = "day") var events: List<Event>,
          @OneToMany(mappedBy = "day") var tasks: List<Task>,
          @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_week") var week: Week,
          val name: String) {

    @Id
    @GeneratedValue
    var _id: Long? = null

}