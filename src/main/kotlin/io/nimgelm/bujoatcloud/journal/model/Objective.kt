package io.nimgelm.bujoatcloud.journal.model

import io.nimgelm.bujoatcloud.journal.util.enum.CompletionStatus
import javax.persistence.*

@Entity
class Objective(var name: String,
                var description: String?,
                var completionStatus: CompletionStatus,
                var completionValidationCron: String?,
                @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_week") var week: Week) {

    @Id
    @GeneratedValue
    var _id: Long? = null
}
