package io.nimgelm.bujoatcloud.journal.model

import org.springframework.data.annotation.Id
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class Day(@OneToMany(mappedBy = "day") var notes: Note,
          @OneToMany(mappedBy = "day") var events: Event,
          @OneToMany(mappedBy = "day") var tasks: Task,
          @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_week") var week: Week) {

    @Id
    @GeneratedValue
    var _id: Long? = null

    val today = ZonedDateTime.now()
}