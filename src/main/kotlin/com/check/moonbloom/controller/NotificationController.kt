package com.check.moonbloom.controller

import com.check.moonbloom.model.CalendarType
import com.check.moonbloom.model.Relationship
import com.check.moonbloom.usecase.Service
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
@RequestMapping("/api")
class NotificationController(
    private val service: Service
) {

    @PostMapping("/notifications")
    fun notify(@RequestBody request: NotificationRequest): ResponseEntity<String> {
        return try {
            val message = service.notifyBirthday(request.dob, request.calendarType, request.relationship, request.name)
            ResponseEntity.ok(message)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.localizedMessage)
        }
    }
}


data class NotificationRequest(
    val dob: LocalDate,
    val calendarType: CalendarType,
    val relationship: Relationship,
    val name: String?
)