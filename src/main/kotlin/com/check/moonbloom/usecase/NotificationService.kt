package com.check.moonbloom.usecase

import com.check.moonbloom.model.*
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class NotificationService {

    // @Transactional(readonly=true)
    fun notifyBirthday(
        phoneNo: String,
        dob: LocalDate,
        calendarType: CalendarType,
        relationship: Relationship,
        name: String
    ): MessageDto {
        if (calendarType != CalendarType.LUNAR && calendarType != CalendarType.GREGORIAN) {
            throw IllegalArgumentException("Invalid Calendar Type")
        }

        val birthday = Birthday(dob, calendarType)
        val honoree = Honoree(name, birthday)
        val user = User(PhoneNo(phoneNo), relationship)

        // TODO: save birthday, honoree, user

        return MessageDto(
            msg = InstantMessage(honoree, user).toString(),
            phoneNo = user.phoneNo
        )
    }
}

data class MessageDto(
    val msg: String,
    val phoneNo: String
)