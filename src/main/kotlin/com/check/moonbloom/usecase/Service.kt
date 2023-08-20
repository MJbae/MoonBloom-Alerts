package com.check.moonbloom.usecase

import com.check.moonbloom.model.*
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class Service {
    fun notifyBirthday(
        dob: LocalDate,
        calendarType: CalendarType,
        relationship: Relationship,
        name: String?
    ): String {
        if (calendarType != CalendarType.LUNAR && calendarType != CalendarType.GREGORIAN) {
            throw IllegalArgumentException("Invalid Calendar Type")
        }

        val birthday = Birthday(dob, calendarType)
        val honoree = Honoree(name ?: "", birthday)
        val user = User(relationship)

        return InstantMessage(honoree, user).txt()
    }
}