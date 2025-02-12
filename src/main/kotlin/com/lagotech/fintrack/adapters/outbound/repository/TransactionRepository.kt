package com.lagotech.fintrack.adapters.outbound.repository

import com.lagotech.fintrack.domain.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long>