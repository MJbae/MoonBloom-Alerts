package com.check.moonbloom.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface HonoreeRepository : JpaRepository<Honoree, Long?> {
    @Query("SELECT h FROM Honoree h WHERE h.birthday = :birthday")
    fun list(@Param("birthday") birthday: Birthday): Set<Honoree>

}