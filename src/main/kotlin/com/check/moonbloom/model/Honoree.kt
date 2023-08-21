package com.check.moonbloom.model

import jakarta.persistence.*
import java.time.LocalDate


@Entity
class Honoree(
    @ManyToOne
    val user: User,
    val name: String,
    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "date", column = Column(name = "date_of_birthday")),
        AttributeOverride(name = "type", column = Column(name = "birthday_calendar_type"))
    )
    private val birthday: Birthday,
    @Enumerated(EnumType.STRING)
    val relationship: Relationship
) {
    @Id
    @TableGenerator(name = "HonoreeIdGenerator", table = "sequence", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HonoreeIdGenerator")
    var id: Long? = null
    val lunarBirthday: LocalDate
        get() = birthday.lunar()

    val gregorianBirthday: LocalDate
        get() = birthday.gregorian()

    val birthdayType: CalendarType
        get() = birthday.type
}