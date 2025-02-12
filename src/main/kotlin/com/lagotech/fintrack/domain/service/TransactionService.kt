package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.TransactionRepository
import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.Transaction
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val repository: TransactionRepository,
    private val entityToDTOMapper: EntityToDTOMapper
) {

    fun save(transaction: Transaction): TransactionDTO {
        return entityToDTOMapper.parseObject(repository.save(transaction), TransactionDTO::class.java)
    }

    fun findById(id: Long): TransactionDTO {

        val transaction = repository.findById(id)
            .orElseThrow { IllegalArgumentException("Transaction with id $id not found") }

        return entityToDTOMapper.parseObject(transaction, TransactionDTO::class.java)
    }

    fun findAll(): List<TransactionDTO> {
        return repository.findAll().let { entityToDTOMapper.parseListObjects(it, TransactionDTO::class.java) }
    }

    fun delete(transaction: Transaction) {
        repository.delete(transaction)
    }

    fun findByBankAccountId(bankAccountId: Long): List<TransactionDTO> {
        return repository.findByBankAccountId(bankAccountId)
            .let { entityToDTOMapper.parseListObjects(it, TransactionDTO::class.java) }
    }
}