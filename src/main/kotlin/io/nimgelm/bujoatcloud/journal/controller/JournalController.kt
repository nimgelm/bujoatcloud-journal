package io.nimgelm.bujoatcloud.journal.controller

import io.nimgelm.bujoatcloud.journal.dto.*
import io.nimgelm.bujoatcloud.journal.exception.*
import io.nimgelm.bujoatcloud.journal.service.DatabaseService
import io.nimgelm.bujoatcloud.journal.util.validator.DTOValidators
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/api/journal")
class JournalController {

    @Autowired
    lateinit var databaseService: DatabaseService

    @PostMapping("/v1/Events")
    fun insertNewEvent(@RequestBody(required = true) eventDTO: EventDTO) : ResponseEntity<ResponseDTO> {
        return try {
            val today = databaseService.getDayForToday()
            val validatedEvent = DTOValidators.validateEventDTO(eventDTO, today)
            val newEvent = databaseService.saveEvent(validatedEvent)

            ResponseEntity(MessageDTO("${newEvent._id}"), HttpStatus.CREATED)

        } catch (e: DatabaseEntryNotFoundException) {
            ResponseEntity(MessageDTO(e.message!!), HttpStatus.NOT_FOUND)
        } catch (e: MissingPropertyException) {
            ResponseEntity(MessageDTO(e.message!!), HttpStatus.BAD_REQUEST)
        } catch (e: DatabaseSaveException) {
            ResponseEntity(MessageDTO(e.message!!), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/v1/Events")
    fun getAllEvents() : ResponseEntity<ResponseDTO> {
        return try {
            val list = databaseService.getAllEventsForToday()
            ResponseEntity(EventListDTO(list), HttpStatus.OK)

        } catch (e: DatabaseEmptyListResultException) {
            ResponseEntity(MessageDTO(e.message!!), HttpStatus.NOT_FOUND)
        }
    }

    //TODO - Add other endpoints
    //TODO - Add logger

}