package com.check.moonbloom.usecase

import com.check.moonbloom.model.Birthday
import com.check.moonbloom.model.CalendarType
import com.check.moonbloom.model.Honoree
import com.check.moonbloom.model.HonoreeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class UpcomingBirthdayFetcher(
    private val honoreeRepository: HonoreeRepository
) {
    @Transactional(readOnly = true)
    fun fetchHonorees(): Set<Honoree> {
        val targetDate = nextLunarBirthday()
        return honoreesByDate(targetDate)
    }

    private fun nextLunarBirthday(): LocalDate {
        val currentLunarDate = Birthday(LocalDate.now(), CalendarType.GREGORIAN).lunar()
        return currentLunarDate.plusWeeks(1)
    }

    private fun honoreesByDate(birthday: LocalDate): Set<Honoree> {
        return honoreeRepository.listByMonthAndDay(birthday.monthValue, birthday.dayOfMonth)
    }
}