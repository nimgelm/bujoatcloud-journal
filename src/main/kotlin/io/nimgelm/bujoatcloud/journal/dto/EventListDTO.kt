package io.nimgelm.bujoatcloud.journal.dto

import io.nimgelm.bujoatcloud.journal.model.Event

data class EventListDTO(val list: List<Event>) : ResponseDTO