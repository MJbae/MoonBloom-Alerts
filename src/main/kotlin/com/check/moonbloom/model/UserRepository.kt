package com.check.moonbloom.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long?> {
    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    fun get(@Param("userId") userId: UserId): User?

    @Query("SELECT u FROM User u WHERE u.number = :number")
    fun get(@Param("number") number: PhoneNo): User?
}