package com.check.moonbloom.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface HonoreeRepository : JpaRepository<Honoree, Long?> {
    @Query("SELECT h FROM Honoree h WHERE MONTH(h.birthday.date) = :month AND DAY(h.birthday.date) = :day")
    fun listByMonthAndDay(@Param("month") month: Int, @Param("day") day: Int): Set<Honoree>
}