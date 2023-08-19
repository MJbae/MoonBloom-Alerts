package com.check.moonbloom.model

class MessageFactory {
    fun createLunar(honoree: Honoree, user: User): String {
        val lunarDate = honoree.birthday.date.toString()
        val gregorianDate = honoree.birthday.gregorianDate().toString()
        val message = LunarMessage(honoree, user)

        if (Math.random() < 0.5) message.case1(lunarDate, gregorianDate)
        return message.case2(lunarDate, gregorianDate)
    }

    fun createGregorian(honoree: Honoree, user: User): String {
        val gregorianDate = honoree.birthday.gregorianDate().toString()
        val message = GregorianMessage(honoree, user)

        if (Math.random() < 0.5) return message.case1(gregorianDate)
        return message.case2(gregorianDate)
    }
}