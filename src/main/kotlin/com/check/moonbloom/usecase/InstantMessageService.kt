package com.check.moonbloom.usecase

import com.check.moonbloom.model.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class InstantMessageService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun sendInstantMsg(
        calendarType: CalendarType,
        phoneNo: String,
        dob: LocalDate,
        relationship: Relationship,
        name: String
    ): MessageDto {
        val user = User(UserId(UniqueIdMaker.id()), PhoneNo(phoneNo))
        val honoree = Honoree(user, name, Birthday(dob, calendarType), relationship)

        user.join(honoree)
        userRepository.save(user)

        val message = InstantBirthdayMessage(honoree)

        return MessageDto(
            msg = message.toString(),
            phoneNo = user.phoneNo
        )
    }
}

data class MessageDto(
    val msg: String,
    val phoneNo: String
)