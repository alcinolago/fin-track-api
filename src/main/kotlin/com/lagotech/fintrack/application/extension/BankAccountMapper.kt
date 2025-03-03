package com.lagotech.fintrack.application.extension

import com.lagotech.fintrack.adapters.inbound.controller.BankAccountController
import com.lagotech.fintrack.application.request.BankAccountRequest
import com.lagotech.fintrack.application.response.BankAccountResponse
import com.lagotech.fintrack.domain.model.BankAccount
import com.lagotech.fintrack.domain.model.User
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

object BankAccountMapper {

    fun toEntity(request: BankAccountRequest, user: User): BankAccount {
        return BankAccount(
            bankName = request.bankName,
            accountNumber = request.accountNumber,
            accountDigit = request.accountDigit,
            agency = request.agency,
            balance = request.balance,
            user = user
        )
    }

    fun toResponse(bank: BankAccount): BankAccountResponse {

        val response = BankAccountResponse(
            id = bank.id,
            bankName = bank.bankName,
            accountNumber = bank.accountNumber,
            accountDigit = bank.accountDigit,
            agency = bank.agency,
            balance = bank.balance,
            userId = bank.user.id!!
        )

        response.add(linkTo(methodOn(BankAccountController::class.java).findById(bank.id!!)).withSelfRel())

        response.add(linkTo(methodOn(BankAccountController::class.java).findByUserId(bank.user.id!!)).withRel("userBankAccounts"))

        return response
    }

    fun toResponseList(accounts: List<BankAccount>): List<BankAccountResponse> {
        return accounts.map { BankAccountMapper.toResponse(it) }
    }
}