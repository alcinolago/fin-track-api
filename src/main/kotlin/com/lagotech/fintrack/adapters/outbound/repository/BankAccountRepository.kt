package com.lagotech.fintrack.adapters.outbound.repository

import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.domain.model.BankAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankAccountRepository : JpaRepository<BankAccount, Long?> {
    fun findByBankNameContaining(name: String): List<BankAccountDTO>
    fun existsByBankName(name: String): Boolean
}