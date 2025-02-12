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

    fun save(transaction: Transaction): TransactionDTO {
        return entityToDTOMapper.parseObject(repository.save(transaction), TransactionDTO::class.java)
    }

    fun findById(id: Long): TransactionDTO {

        val transaction = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Transaction with id $id not found") }

        return entityToDTOMapper.parseObject(transaction, TransactionDTO::class.java)
    }

    fun findAll(): List<TransactionDTO> {

        val transactions = repository.findAll()

        if(transactions.isEmpty()){
            throw ResourceNotFoundException("Recurso não encontrado")
        }

        return entityToDTOMapper.parseListObjects(transactions, TransactionDTO::class.java)
    }

    fun delete(transaction: Transaction) {
        repository.delete(transaction)
    }
}