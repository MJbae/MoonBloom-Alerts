package com.check.moonbloom.usecase

import com.check.moonbloom.model.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class NotificationService {

     @Transactional
    fun notifyBirthday(
        calendarType: CalendarType,
        phoneNo: String,
        dob: LocalDate,
        relationship: Relationship,
        name: String
    ): MessageDto {
        val birthday = Birthday(dob, calendarType)
        val user = User(PhoneNo(phoneNo))
        val honoree = Honoree(user, name, birthday, relationship)

        // TODO: save birthday, honoree, user

        return MessageDto(
            msg = InstantMessage(honoree).toString(),
            phoneNo = user.phoneNo
        )
    }
}

data class MessageDto(
    val msg: String,
    val phoneNo: String
)