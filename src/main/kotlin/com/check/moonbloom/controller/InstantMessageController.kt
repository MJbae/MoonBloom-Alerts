package com.check.moonbloom.controller

import com.check.moonbloom.model.CalendarType
import com.check.moonbloom.model.Relationship
import com.check.moonbloom.usecase.MessageDto
import com.check.moonbloom.usecase.InstantMessageService
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
@RequestMapping("/api")
class NotificationController(
    private val service: InstantMessageService
) {

    @PostMapping("/notifications")
    fun notify(@RequestBody req: NotificationRequest): MessageDto {
        return service.sendInstantMsg(
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