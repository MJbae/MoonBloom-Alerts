package com.check.moonbloom.model

import java.time.LocalDate

class Birthday(private val date: LocalDate) {
    fun lunar(): LocalDate = lunarToGregorian(thisBirthday(date))
    fun gregorian(): LocalDate = thisBirthday(date)
}

private fun thisBirthday(originalDate: LocalDate) =
    LocalDate.of(LocalDate.now().year, originalDate.month, originalDate.dayOfMonth)

private fun lunarToGregorian(date: LocalDate) = date.plusDays(29)


enum class CalendarType {
    LUNAR,
    GREGORIAN
}
