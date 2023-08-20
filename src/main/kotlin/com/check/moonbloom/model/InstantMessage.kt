package com.check.moonbloom.model

class InstantMessage(private val honoree: Honoree, private val user: User) {

    fun txt(): String {
        if (honoree.birthdayType == CalendarType.LUNAR) return lunarMsg()
        return gregorianMsg()
    }

    private fun lunarMsg() =
        "올해 ${user.relationship}의 생신은 양력으로 보면 ${honoree.gregorianBirthday}입니다! 🎂💕"

    private fun gregorianMsg() =
        "올해 ${user.relationship}의 생신은 ${honoree.gregorianBirthday}이에요! 그날을 위해 미리 준비하는 건 어떨까요? 🎂💖"

}