package com.check.moonbloom.model

data class InstantMessage(private val honoree: Honoree, private val user: User) {

    override fun toString(): String {
        if (honoree.birthdayType == CalendarType.LUNAR) return lunarMsg()
        return gregorianMsg()
    }

    private fun lunarMsg() =
        "올해 ${user.relationship}의 생신은 양력으로 보면 ${honoree.gregorianBirthday}입니다! 🎂💕"

    private fun gregorianMsg() =
        "올해 ${user.relationship}의 생신은 ${honoree.gregorianBirthday}이에요! 🎂💖"

}