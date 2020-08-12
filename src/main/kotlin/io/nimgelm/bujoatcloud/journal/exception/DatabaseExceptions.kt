package io.nimgelm.bujoatcloud.journal.exception

import java.lang.RuntimeException

class DatabaseEntryNotFoundException(message: String): RuntimeException(message)

class DatabaseSaveException(message: String): RuntimeException(message)

class DatabaseEmptyListResultException(message: String): RuntimeException(message)