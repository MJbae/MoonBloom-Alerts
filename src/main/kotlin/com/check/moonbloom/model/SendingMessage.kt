package com.check.moonbloom.model


class SendingMessage(private val honoree: Honoree, private val user: User) {

    fun txt(): String {
        if (honoree.birthdayType == CalendarType.LUNAR) return lunarMsg()
        return gregorianMsg()
    }

    private fun lunarMsg() =
        "${user.relationship} ${honoree.name}님의 생신이 벌써 1주일 앞이에요! 🎉\n\n올해 ${user.relationship}의 생신은 음력 ${honoree.lunarBirthday}이에요.\n날짜를 양력으로 바꾸면 ${honoree.gregorianBirthday}입니다! 🎂💕"

    private fun gregorianMsg() =
        "${user.relationship} ${honoree.name}님의 생신이 얼마 안 남았어요, 딱 일주일만 더 기다리면 돼요! 🎉\n\n${user.relationship}의 생신은 ${honoree.gregorianBirthday}이에요! 기억해둬서 깜짝 선물 준비하면 좋을 것 같아요 🎂💖"

}
