package com.lagotech.fintrack.application.extension

import com.lagotech.fintrack.adapters.inbound.controller.TransactionController
import com.lagotech.fintrack.application.request.TransactionRequest
import com.lagotech.fintrack.application.response.TransactionResponse
import com.lagotech.fintrack.domain.model.BankAccount
import com.lagotech.fintrack.domain.model.Transaction
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

object TransactionMapper {

    fun toEntity(bankAccount: BankAccount, request: TransactionRequest): Transaction {
        return Transaction(
            bankAccount = bankAccount,
            amount = request.amount,
            type = request.type,
            dueDate = request.dueDate,
            status = request.status
        )
    }

    fun toResponse(transaction: Transaction): TransactionResponse {
        val response = TransactionResponse(
            id = transaction.id!!,
            amount = transaction.amount,
            type = transaction.type,
            dueDate = transaction.dueDate,
            status = transaction.status,
            bankAccountId = transaction.bankAccount.id!!
        )

        response.add(linkTo(methodOn(TransactionController::class.java).findById(transaction.id!!)).withSelfRel())

        return response
    }

    fun toResponseList(transactions: List<Transaction>): List<TransactionResponse> {
        return transactions.map { toResponse(it) }
    }
}