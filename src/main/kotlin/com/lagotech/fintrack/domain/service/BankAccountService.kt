package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.BankAccountRepository
import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.BankAccount
import org.springframework.stereotype.Service

@Service
class BankAccountService(
    private val repository: BankAccountRepository,
    private val entityToDTOMapper: EntityToDTOMapper
) {

    fun save(bankAccount: BankAccountDTO): BankAccountDTO {

        if (existsByBankName(bankAccount.bankName)) {
            throw IllegalArgumentException("Banco com o nome ${bankAccount.bankName} já existe")
        }

        val entity = entityToDTOMapper.parseObject(bankAccount, BankAccount::class.java)
        val savedBankAccount = repository.save(entity)

        return entityToDTOMapper.parseObject(savedBankAccount, BankAccountDTO::class.java)
    }

    fun findByName(name: String): List<BankAccountDTO> {
        val bankAccounts = repository.findByBankNameContaining(name)

        if (bankAccounts.isEmpty()) {
            throw ResourceNotFoundException("Recurso não encontrado")
        }
        return entityToDTOMapper.parseListObjects(bankAccounts, BankAccountDTO::class.java)
    }

    fun findAll(): List<BankAccountDTO> {

        val bankAccopunts = repository.findAll()

        if (bankAccopunts.isEmpty()) {
            throw ResourceNotFoundException("Recurso não encontrado")
        }
        return entityToDTOMapper.parseListObjects(bankAccopunts, BankAccountDTO::class.java)
    }

    fun findById(id: Long): BankAccountDTO {
        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Recurso não encontrado") }
            .let { bankAccount -> entityToDTOMapper.parseObject(bankAccount, BankAccountDTO::class.java) }
    }

    fun existsByBankName(name: String): Boolean {
        return repository.existsByBankName(name)
    }

    fun delete(bankAccount: BankAccount) {
        repository.delete(bankAccount)
    }
}