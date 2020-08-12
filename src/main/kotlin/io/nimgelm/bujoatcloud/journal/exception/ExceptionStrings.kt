package io.nimgelm.bujoatcloud.journal.exception

import java.lang.StringBuilder

object ExceptionStrings {
    fun missingMandatoryPropertyInDTO(whichObject: String, whichField: String) : String =
            "Missing mandatory property $whichField for new object $whichObject."

    fun databaseEntryNotFound(whichTable: String, whichId: String) : String =
            "Could not find an existing $whichTable entry with value $whichId."

    fun databaseEmptyListResult(whichTable: String, filter: String) : String =
            "The list of $whichTable for $filter is empty."

    fun databaseFailedSave(whichTable: String, whichId: String, details: String?) : String {
        val strBuilder = StringBuilder()
        strBuilder.append("Failed to save new $whichTable entry with value $whichId.")

        if (! details.isNullOrEmpty()) {
            strBuilder.append("\nFurther Details: $details.")
        }

        return strBuilder.toString()
    }

}