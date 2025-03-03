package com.lagotech.fintrack.adapters.outbound.repository


import com.lagotech.fintrack.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long?> {
    fun existsByEmail(email: String): Boolean
}