package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.BankAccountRepository
import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.BankAccount
import org.springframework.stereotype.Service
import java.util.*

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

    fun findById(id: Long): Optional<BankAccountDTO> {
        return repository.findById(id)
            .map { entityToDTOMapper.parseObject(it, BankAccountDTO::class.java) }
    }

    fun existsByBankName(name: String): Boolean {
        return repository.existsByBankName(name)
    }

    fun update(id: Long, bankAccountDTO: BankAccountDTO): BankAccountDTO {
        val existingAccount = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Account bank com ID $id não encontrada") }

        // Atualizando os campos
        existingAccount.bankName = bankAccountDTO.bankName
        existingAccount.accountNumber = bankAccountDTO.accountNumber
        existingAccount.accountDigit = bankAccountDTO.accountDigit
        existingAccount.accountDigit = bankAccountDTO.accountDigit
        existingAccount.agency = bankAccountDTO.agency

        val savedAccountBank = repository.save(existingAccount)

        return entityToDTOMapper.parseObject(savedAccountBank, BankAccountDTO::class.java)
    }

    fun delete(bankId: Long) {
        val bank = repository.findById(bankId)
            .orElseThrow { ResourceNotFoundException("Account Bank with id $bankId not found") }

        repository.delete(bank)
    }
}