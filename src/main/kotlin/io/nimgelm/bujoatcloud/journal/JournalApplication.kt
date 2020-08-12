package io.nimgelm.bujoatcloud.journal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class JournalApplication

fun main(args: Array<String>) {
	runApplication<JournalApplication>(*args)
}
