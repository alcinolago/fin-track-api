package com.lagotech.fintrack.adapters.outbound.repository

import com.lagotech.fintrack.domain.model.SavingsGoal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SavingsGoalRepository : JpaRepository<SavingsGoal, Long?> {
    fun findByUserId(userId: Long): List<SavingsGoal>
}