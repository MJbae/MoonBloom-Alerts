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

        return MessageDto(
            phoneNo = user.phoneNoInDash,
            honoree = HonoreeDto(
                relationship = honoree.relationship.toString(),
                gregorianBirthday = honoree.gregorianBirthday
            )
        )
    }
}

data class MessageDto(
    val honoree: HonoreeDto,
    val phoneNo: String
)

data class HonoreeDto(
    val relationship: String,
    val gregorianBirthday: LocalDate
)