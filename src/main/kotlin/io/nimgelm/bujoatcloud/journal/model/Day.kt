package io.nimgelm.bujoatcloud.journal.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class Day(@OneToMany(mappedBy = "day") var notes: List<Note>,
          @OneToMany(mappedBy = "day") var events: List<Event>,
          @OneToMany(mappedBy = "day") var tasks: List<Task>,
          @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_week") var week: Week) {

    @Id
    @GeneratedValue
    var _id: Long? = null

    val today = ZonedDateTime.now()
}