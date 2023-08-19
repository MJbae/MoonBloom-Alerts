package com.check.moonbloom.model

class LunarMessage(private val honoree: Honoree, private val user: User) {

    fun case1(lunarDate: String, gregorianDate: String) =
        "${user.relationship} ${honoree.name}님의 생신이 벌써 1주일 앞이에요! 🎉\n\n올해 ${user.relationship}의 생신은 음력 $lunarDate 이에요.\n날짜를 양력으로 바꾸면 $gregorianDate 랍니다! 🎂💕"

    fun case2(lunarDate: String, gregorianDate: String) =
        "우와, ${user.relationship} ${honoree.name}님의 생신이 어느덧 일주일 앞으로 다가왔네요! 🎉\n\n올해 ${user.relationship}의 생신은 음력 $lunarDate 로, 양력으로 보면 $gregorianDate 이에요!"
}

class GregorianMessage(private val honoree: Honoree, private val user: User) {

    fun case1(gregorianDate: String) =
        "${user.relationship} ${honoree.name}님의 생신이 얼마 안 남았어요, 딱 일주일만 더 기다리면 돼요! 🎉\n\n${user.relationship}의 생신은 $gregorianDate 이에요! 기억해둬서 깜짝 선물 준비하면 좋을 것 같아요 🎂💖"

    fun case2(gregorianDate: String) =
        "${user.relationship} ${honoree.name}님의 생신까지 얼마 남지 않았네요! 한 주 후면 그 특별한 날이에요! 🎉\n\n${user.relationship}의 생신은 $gregorianDate 랍니다! 그날을 위해 미리 준비하는 건 어떨까요? 🎂💕"
}
