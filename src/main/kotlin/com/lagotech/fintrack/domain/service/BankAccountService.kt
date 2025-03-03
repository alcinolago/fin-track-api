package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.BankAccountRepository
import com.lagotech.fintrack.application.extension.BankAccountMapper
import com.lagotech.fintrack.application.response.BankAccountResponse
import com.lagotech.fintrack.domain.model.BankAccount
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BankAccountService(
    private val repository: BankAccountRepository
) {

    @Transactional
    fun save(req: BankAccount): BankAccount {
        return repository.save(req)
    }

    fun findById(id: Long): Optional<BankAccount> {
        return repository.findById(id)
    }

    fun findByUserId(userId: Long): List<BankAccount> {
        return repository.findByUserId(userId)
    }

    fun update(bankAccount: BankAccount): BankAccountResponse {
        return BankAccountMapper.toResponse(repository.save(bankAccount))
    }

    fun delete(bankAccount: BankAccount) {
        repository.delete(bankAccount)
    }
}