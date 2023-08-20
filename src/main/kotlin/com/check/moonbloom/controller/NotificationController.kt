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
            phoneNo = req.phoneNo,
            dob = req.dob,
            calendarType = req.calendarType,
            relationship = req.relationship,
            name = req.name
        )
    }
}


data class NotificationRequest(
    val phoneNo: String,
    val dob: LocalDate,
    val calendarType: CalendarType,
    val relationship: Relationship,
    val name: String
)