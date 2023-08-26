package com.check.moonbloom

import com.check.moonbloom.model.*
import java.time.LocalDate

object TestFactory {
    private val random = java.util.Random()

    fun createHonoree(
        user: User = createUser(),
        name: String = "Test${random.nextInt(100)}",
        birthday: Birthday = createBirthday(),
        relationship: Relationship = Relationship.values().random()
    ): Honoree {
        return Honoree(user, name, birthday, relationship)
    }

    fun createUser(
        userId: UserId = UserId(UniqueIdMaker.id()),
        phoneNo: PhoneNo = PhoneNo(randomPhoneNo())
    ): User {
        return User(userId, phoneNo)
    }

    fun createBirthday(
        date: LocalDate = randomDate(),
        type: CalendarType = CalendarType.LUNAR
    ): Birthday {
        return Birthday(date, type)
    }

    fun randomName(): String {
        return "Test${random.nextInt(1000)}"
    }

    fun randomPhoneNo(): String {
        return "010" + (1..8).joinToString("") { random.nextInt(10).toString() }
    }


    fun randomDate(): LocalDate {
        val year = random.nextInt(1980 - 1930 + 1) + 1930
        val month = random.nextInt(12) + 1
        val dayOfMonth = random.nextInt(LocalDate.of(year, month, 1).lengthOfMonth()) + 1
        return LocalDate.of(year, month, dayOfMonth)
    }
}
