package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.TransactionRepository
import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.Transaction
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val repository: TransactionRepository,
    private val entityToDTOMapper: EntityToDTOMapper
) {

    fun save(transactionDTO: TransactionDTO): TransactionDTO {

        val entity = entityToDTOMapper.parseObject(transactionDTO, Transaction::class.java)

        val savedTransaction = repository.save(entity)

        return entityToDTOMapper.parseObject(savedTransaction, TransactionDTO::class.java)
    }

    fun findById(id: Long): TransactionDTO {

        val transaction = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Transaction with id $id not found") }

        return entityToDTOMapper.parseObject(transaction, TransactionDTO::class.java)
    }

    fun findAll(): List<TransactionDTO> {

        val transactions = repository.findAll()

        if (transactions.isEmpty()) {
            throw ResourceNotFoundException("Recurso não encontrado")
        }

        return entityToDTOMapper.parseListObjects(transactions, TransactionDTO::class.java)
    }

    fun update(id: Long, transactionDTO: TransactionDTO): TransactionDTO {

        val existingTransaction = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Transaction com ID $id não encontrada") }

        transactionDTO.transactionType?.let {
            existingTransaction.transactionType = it
        }
        transactionDTO.categoryId?.let {
            existingTransaction.categoryId = it
        }
        transactionDTO.bankId?.let {
            existingTransaction.bankId = it
        }
        transactionDTO.amount?.let {
            existingTransaction.amount = it
        }
        transactionDTO.transactionDate?.let {
            existingTransaction.transactionDate = it
        }
        existingTransaction.notified = transactionDTO.notified

        val savedTransaction = repository.save(existingTransaction)

        return entityToDTOMapper.parseObject(savedTransaction, TransactionDTO::class.java)
    }

    fun delete(transactionId: Long) {
        val transaction = repository.findById(transactionId)
            .orElseThrow { ResourceNotFoundException("Account Bank with id $transactionId not found") }

        repository.delete(transaction)
    }
}