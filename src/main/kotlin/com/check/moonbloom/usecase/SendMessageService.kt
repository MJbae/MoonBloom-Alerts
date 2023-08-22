package com.check.moonbloom.usecase

import org.springframework.stereotype.Service

@Service
class SendMessageService(
    private val upcomingBirthdayFetcher: UpcomingBirthdayFetcher,
    private val smsService: NaverSmsService
) {
    fun sendForHonorees() {
        val upcomingHonorees = upcomingBirthdayFetcher.fetchHonorees()
        upcomingHonorees.forEach { smsService.sendFor(it) }
    }
}