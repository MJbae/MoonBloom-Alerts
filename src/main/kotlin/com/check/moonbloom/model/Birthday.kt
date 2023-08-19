package com.check.moonbloom.model

import com.github.usingsky.calendar.KoreanLunarCalendar
import java.time.LocalDate


class Birthday(private val date: LocalDate) {
    private var lunarConverter = KoreanLunarCalendar.getInstance()

    fun lunar(): LocalDate = thisBirthday(date)
    fun gregorian(): LocalDate = lunarToGregorian(thisBirthday(date))


    private fun thisBirthday(originalDate: LocalDate) =
        LocalDate.of(LocalDate.now().year, originalDate.month, originalDate.dayOfMonth)

    private fun lunarToGregorian(date: LocalDate): LocalDate {
        lunarConverter.setLunarDate(LocalDate.now().year, date.month.value, date.dayOfMonth, false)
        return LocalDate.of(lunarConverter.solarYear, lunarConverter.solarMonth, lunarConverter.solarDay)
    }
}


enum class CalendarType {
    LUNAR,
    GREGORIAN
}
