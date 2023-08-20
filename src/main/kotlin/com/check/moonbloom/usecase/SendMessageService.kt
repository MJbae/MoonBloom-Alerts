package com.check.moonbloom.usecase

import com.check.moonbloom.model.Birthday
import com.check.moonbloom.model.CalendarType
import com.check.moonbloom.model.Honoree
import com.check.moonbloom.model.HonoreeRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SendMessageService(
    private val honoreeRepository: HonoreeRepository,
    private val smsService: NaverSmsService
) {
    fun send() {
        val dayBeforeWeek = LocalDate.now().minusWeeks(1)
        val targetBirthday = Birthday(dayBeforeWeek, CalendarType.LUNAR)
        val honorees: Set<Honoree> = honoreeRepository.list(targetBirthday)

        honorees.forEach { smsService.send(it) }
    }
}