package com.check.moonbloom.model


data class SmsMessage(private val honoree: Honoree) {
    override fun toString(): String {
        if (honoree.birthdayType == CalendarType.LUNAR) return lunarMsg()
        return gregorianMsg()
    }

    private fun lunarMsg() =
        "${honoree.name}님의 생신이 1주일 전이에요! 올해 ${honoree.relationship}의 생신은 양력으로 보면 ${honoree.gregorianBirthday}입니다!"

    private fun gregorianMsg() =
        "${honoree.name}님의 생신은 일주일만 더 기다리면 돼요! \n\n${honoree.relationship}의 생신은 ${honoree.gregorianBirthday}이에요!"

}
