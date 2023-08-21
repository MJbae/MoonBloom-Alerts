package com.check.moonbloom

import com.check.moonbloom.model.*
import java.time.LocalDate

object DomainFactory {
    fun createHonoree(
        user: User = createUser(),
        name: String = "John Doe",
        birthday: Birthday = createBirthday(),
        relationship: Relationship = Relationship.OTHER
    ): Honoree {
        return Honoree(user, name, birthday, relationship)
    }

    fun createUser(
        userId: UserId = UserId(UniqueIdMaker.id()),
        phoneNo: PhoneNo = PhoneNo("01012345678")
    ): User {
        return User(userId, phoneNo)
    }

    fun createBirthday(
        date: LocalDate = LocalDate.now(),
        type: CalendarType = CalendarType.LUNAR
    ): Birthday {
        return Birthday(date, type)
    }
}
