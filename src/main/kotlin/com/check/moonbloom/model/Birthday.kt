package com.check.moonbloom.model

import com.github.usingsky.calendar.KoreanLunarCalendar
import java.time.LocalDate


class Birthday(private val date: LocalDate, val type: CalendarType) {
    private var lunarConverter = KoreanLunarCalendar.getInstance()

    internal fun lunar(): LocalDate {
        if (type == CalendarType.LUNAR) return thisBirthday(date)
        return gregorianToLunar(thisBirthday(date))
    }

    internal fun gregorian(): LocalDate {
        if (type == CalendarType.GREGORIAN) return thisBirthday(date)
        return lunarToGregorian(thisBirthday(date))
    }

    private fun thisBirthday(date: LocalDate) = LocalDate.of(LocalDate.now().year, date.month, date.dayOfMonth)

    private fun lunarToGregorian(date: LocalDate): LocalDate {
        lunarConverter.setLunarDate(date.year, date.month.value, date.dayOfMonth, false)
        return LocalDate.of(lunarConverter.solarYear, lunarConverter.solarMonth, lunarConverter.solarDay)
    }

    private fun gregorianToLunar(date: LocalDate): LocalDate {
        lunarConverter.setSolarDate(date.year, date.month.value, date.dayOfMonth)
        return LocalDate.of(lunarConverter.lunarYear, lunarConverter.lunarMonth, lunarConverter.lunarDay)
    }
}


enum class CalendarType {
    LUNAR,
    GREGORIAN
}
