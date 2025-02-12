package com.lagotech.fintrack.adapters.outbound.repository

import com.lagotech.fintrack.domain.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByBankAccountId(bankAccountId: Long): List<Transaction>
    fun findByExpenseCategoryId(expenseCategoryId: Long): List<Transaction>
    fun findByBankAccountIdAndExpenseCategoryId(bankAccountId: Long, expenseCategoryId: Long): List<Transaction>
    fun existsByBankAccountIdAndExpenseCategoryId(bankAccountId: Long, expenseCategoryId: Long): Boolean
    fun existsByBankAccountId(bankAccountId: Long): Boolean
    fun existsByExpenseCategoryId(expenseCategoryId: Long): Boolean
}