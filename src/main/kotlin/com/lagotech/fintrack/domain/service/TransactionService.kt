package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.TransactionRepository
import com.lagotech.fintrack.application.extension.TransactionMapper
import com.lagotech.fintrack.application.response.TransactionResponse
import com.lagotech.fintrack.domain.model.Transaction
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService(
    private val repository: TransactionRepository,
    private val assembler: PagedResourcesAssembler<TransactionResponse>
) {

    fun save(transaction: Transaction): Transaction {
        return repository.save(transaction)
    }

    fun findById(id: Long): Optional<Transaction> {
        return repository.findById(id)
    }

    fun findByUserId(userId: Long, pageable: Pageable): PagedModel<EntityModel<TransactionResponse>> {

        val transactionsPage = repository.findByUserId(userId, pageable)

        return assembler.toModel(transactionsPage.map(TransactionMapper::toResponse))
    }

    fun update(transaction: Transaction): TransactionResponse {
        return TransactionMapper.toResponse(repository.save(transaction))
    }

    fun delete(transaction: Transaction) {
        repository.delete(transaction)
    }
}