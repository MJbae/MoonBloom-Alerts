package com.check.moonbloom.usecase

import com.check.moonbloom.model.Birthday
import com.check.moonbloom.model.CalendarType
import com.check.moonbloom.model.HonoreeRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SendMessageService(
    private val honoreeRepository: HonoreeRepository,
    private val smsService: NaverSmsService
) {
    fun send() {
        val birthday = getTargetBirthday()
        val originalBirthday = birthday.lunar().plusWeeks(1)
        val honorees = honoreeRepository.listByMonthAndDay(originalBirthday.monthValue, originalBirthday.dayOfMonth)

        honorees.forEach { smsService.send(it) }
    }

    private fun getTargetBirthday(): Birthday {
        val targetLunarDate = Birthday(LocalDate.now(), CalendarType.GREGORIAN).lunar()
        return Birthday(LocalDate.of(targetLunarDate.year, targetLunarDate.month, targetLunarDate.dayOfMonth), CalendarType.LUNAR)
    }
}