package com.check.moonbloom.model

import jakarta.persistence.*
import java.util.*

@Entity
class User(
    @Embedded
    @AttributeOverride(name = "userId", column = Column(name = "userId", unique = true, length = 40))
    val userId: UserId,
    @Embedded
    @AttributeOverride(name = "number", column = Column(name = "phone_no", unique = true))
    val number: PhoneNo
) {
    @Id
    @TableGenerator(name = "userIdGenerator", table = "sequence", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userIdGenerator")
    var id: Long? = null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val honorees: MutableSet<Honoree> = mutableSetOf()

    val phoneNo: String
        get() = number.toString()


    val phoneNoInDash: String
        get() = number.withDash()

    fun join(honoree: Honoree) {
        honorees.add(honoree)
    }
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

@Embeddable
data class UserId(
    private val userId: String
) {
    override fun toString(): String {
        return this.userId
    }
}

object UniqueIdMaker {
    fun id() = UUID.randomUUID().toString().replace("-", "")
}

@Embeddable
data class PhoneNo(
    private val number: String
) {
    override fun toString(): String {
        return number
    }

    fun withDash(): String {
        return "${number.substring(0, 3)}-${number.substring(3, 7)}-${number.substring(7, 11)}"
    }
}