package com.lagotech.fintrack.adapters.outbound.repository

import com.lagotech.fintrack.domain.model.ExpenseCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpenseCategoryRepository : JpaRepository<ExpenseCategory, Long?> {

    fun findByNameContaining(name: String): List<ExpenseCategory>

    fun existsByName(name: String): Boolean
}