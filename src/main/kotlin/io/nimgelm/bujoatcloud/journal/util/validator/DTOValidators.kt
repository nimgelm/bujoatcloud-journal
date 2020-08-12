package io.nimgelm.bujoatcloud.journal.util.validator

import io.nimgelm.bujoatcloud.journal.dto.EventDTO
import io.nimgelm.bujoatcloud.journal.exception.ExceptionStrings
import io.nimgelm.bujoatcloud.journal.exception.MissingPropertyException
import io.nimgelm.bujoatcloud.journal.model.Day
import io.nimgelm.bujoatcloud.journal.model.Event

object DTOValidators {

    fun validateEventDTO(eventDTO: EventDTO, today: Day) : Event {

        if (eventDTO.name.isNullOrEmpty()) {
            throw MissingPropertyException(
                    ExceptionStrings.missingMandatoryPropertyInDTO("Event", "Name"))
        }

        if (eventDTO.schedule == null) {
            throw MissingPropertyException(
                    ExceptionStrings.missingMandatoryPropertyInDTO("Event", "Schedule"))
        }

        return Event(eventDTO.name!!,
                eventDTO.description,
                eventDTO.schedule!!,
                today)
    }
}