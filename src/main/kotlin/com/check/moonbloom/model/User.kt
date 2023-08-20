package com.check.moonbloom.model

import java.time.LocalDate


data class Honoree(val name: String, private val birthday: Birthday) {
    val lunarBirthday: LocalDate
        get() = birthday.lunar()

    val gregorianBirthday: LocalDate
        get() = birthday.gregorian()

    val birthdayType: CalendarType
        get() = birthday.type
}

data class User(
    val number: PhoneNo,
    val relationship: Relationship
) {
    val phoneNo: String
        get() = number.toString()
}

enum class Relationship(private val inKorean: String) {
    MOTHER("엄마"),
    FATHER("아빠"),
    GRANDMA("할머니"),
    GRANDPA("할아버지"),
    BROTHER("형제"),
    SISTER("자매"),
    UNCLE("삼촌"),
    AUNT("숙모"),
    COUSIN("사촌"),
    OTHER("지인");

    override fun toString(): String {
        return inKorean
    }
}

data class PhoneNo(private val rawNumber: String) {
    private val number = rawNumber.replace("-", "")

    override fun toString(): String {
        return "${number.substring(0, 3)}-${number.substring(3, 7)}-${number.substring(7, 11)}"
    }
}