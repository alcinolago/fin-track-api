package com.lagotech.fintrack.adapters.outbound.repository

import com.lagotech.fintrack.domain.model.TransactionCategory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionCategoryRepository : JpaRepository<TransactionCategory, Long?> {
    override fun findAll(pageable: Pageable): Page<TransactionCategory>
    fun existsByName(name: String): Boolean
}