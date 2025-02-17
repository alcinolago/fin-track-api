package com.lagotech.fintrack.adapters.outbound.repository

import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.domain.model.ExpenseCategory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpenseCategoryRepository : JpaRepository<ExpenseCategory, Long?> {

    override fun findAll(pageable: Pageable): Page<ExpenseCategory>

    fun findByNameContaining(name: String): List<ExpenseCategory>

    fun existsByName(name: String): Boolean
}