package com.lagotech.fintrack.adapters.outbound.repository

import com.lagotech.fintrack.domain.model.Transaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {

    @EntityGraph(attributePaths = ["category", "bankAccount"])
    @Query("SELECT t FROM Transaction t")
    fun findAllWithDetails(pageable: Pageable): Page<Transaction>
}