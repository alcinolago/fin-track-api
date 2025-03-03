package com.lagotech.fintrack.adapters.outbound.repository

import com.lagotech.fintrack.domain.model.Transaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.bankAccount.user.id = :userId")
    fun findByUserId(@Param("userId") userId: Long, pageable: Pageable): Page<Transaction>
}