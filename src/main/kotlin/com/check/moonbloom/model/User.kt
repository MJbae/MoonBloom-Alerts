package com.check.moonbloom.model


data class Honoree(val name: String, val birthday: Birthday)
data class User(val relationship: Relationship)

enum class Relationship(private val inKorean: String) {
    MOTHER("엄마"),
    FATHER("아빠"),
    GRANDMA("할머니"),
    GRANDPA("할아버지"),
    BROTHER("형/누나/동생"),
    SISTER("언니/여동생"),
    UNCLE("삼촌"),
    AUNT("숙모"),
    COUSIN("사촌"),
    OTHER("지인");

    override fun toString(): String {
        return inKorean
    }
}