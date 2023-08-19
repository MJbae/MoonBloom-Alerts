package com.check.MoonBloom.Alerts.usecase

import com.check.MoonBloom.Alerts.model.*
import java.time.LocalDate


class Service {
    private val messageFactory = MessageFactory()

    fun notifyBirthday(
        dob: LocalDate,
        calendarType: CalendarType,
        relationship: String,
        name: String?
    ): String {
        if (calendarType != CalendarType.LUNAR && calendarType != CalendarType.GREGORIAN) {
            throw IllegalArgumentException("Invalid Calendar Type")
        }

        val birthday = Birthday(dob, calendarType)
        val honoree = Honoree(name ?: "", birthday)
        val user = User(relationship)

        if (calendarType == CalendarType.GREGORIAN) return messageFactory.createGregorian(honoree, user)
        return messageFactory.createLunar(honoree, user)
    }
}