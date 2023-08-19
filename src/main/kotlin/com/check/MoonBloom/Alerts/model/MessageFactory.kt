package com.check.MoonBloom.Alerts.model

class MessageFactory {
    fun createLunar(honoree: Honoree, user: User): String {
        val lunarDate = honoree.birthday.date.toString()
        val gregorianDate = honoree.birthday.gregorianDate().toString()
        val message = LunarMessage(honoree, user)

        // Randomly choose between case1 and case2 for variety.
        if (Math.random() < 0.5) message.case1(lunarDate, gregorianDate)

        return message.case2(lunarDate, gregorianDate)
    }

    fun createGregorian(honoree: Honoree, user: User): String {
        val gregorianDate = honoree.birthday.gregorianDate().toString()
        val message = GregorianMessage(honoree, user)

        // Randomly choose between case1 and case2 for variety.
        if (Math.random() < 0.5) return message.case1(gregorianDate)

        return message.case2(gregorianDate)
    }
}