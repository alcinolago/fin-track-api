package com.lagotech.fintrack.adapters.outbound.repository

import com.lagotech.fintrack.domain.model.Budget
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BudgetRepository : JpaRepository<Budget, Long?> {
    fun findByUserId(userId: Long): List<Budget>
}