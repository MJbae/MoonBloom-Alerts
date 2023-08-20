package com.check.moonbloom.controller

import com.check.moonbloom.model.CalendarType
import com.check.moonbloom.model.Relationship
import com.check.moonbloom.usecase.MessageDto
import com.check.moonbloom.usecase.NotificationService
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
@RequestMapping("/api")
class NotificationController(
    private val service: NotificationService
) {

    @PostMapping("/notifications")
    fun notify(@RequestBody req: NotificationRequest): MessageDto {
        return service.notifyBirthday(
            dob = req.dob,
            name = req.name,
            phoneNo = req.phoneNo,
            relationship = req.relationship,
            calendarType = req.calendarType ?: CalendarType.LUNAR
        )
    }
}


data class NotificationRequest(
    val calendarType: CalendarType?,
    val phoneNo: String,
    val dob: LocalDate,
    val relationship: Relationship,
    val name: String
)