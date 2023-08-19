package com.check.MoonBloom.Alerts.model

import java.time.LocalDate

class Birthday(val date: LocalDate, private val calendarType: CalendarType) {
    fun gregorianDate(): LocalDate {
        return when (calendarType) {
            CalendarType.LUNAR -> convertLunarToGregorian()
            CalendarType.GREGORIAN -> date
        }
    }

    private fun convertLunarToGregorian(): LocalDate {
        // Placeholder logic; in reality, the conversion is more complex.
        return date.plusDays(29)
    }
}


enum class CalendarType {
    LUNAR,
    GREGORIAN
}