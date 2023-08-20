package com.check.moonbloom.controller

import com.check.moonbloom.usecase.SendMessageService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class NotificationScheduler(
    private val service: SendMessageService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Scheduled(cron = EVERY_10_AM, zone = KOREA_ZONE)
    fun sendNotification() {
        logger.info("notification scheduler started ${LocalDateTime.now()}")
        service.send()
    }

    companion object {
        const val KOREA_ZONE = "Asia/Seoul"
        const val EVERY_10_AM = "0 0 10 * * *"
    }
}
