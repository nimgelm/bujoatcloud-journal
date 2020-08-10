package io.nimgelm.bujoatcloud.journal.model

import io.nimgelm.bujoatcloud.journal.util.enum.RetrospectiveType
import javax.persistence.*

@Entity
class Retrospective(var name:String,
                    var description: String?,
                    var type: RetrospectiveType,
                    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_month") val month: Month) {

    @Id
    @GeneratedValue
    var _id: Long? = null
}