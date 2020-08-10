package io.nimgelm.bujoatcloud.journal.model

import javax.persistence.*

@Entity
class Note(var name: String,
           var description: String?,
           @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_day") var day: Day) {

    @Id
    @GeneratedValue
    var _id: Long? = null
}