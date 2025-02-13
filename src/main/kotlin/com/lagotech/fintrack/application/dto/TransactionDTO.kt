package com.lagotech.fintrack.application.dto

import com.lagotech.fintrack.domain.model.BankAccount
import com.lagotech.fintrack.domain.model.ExpenseCategory
import com.lagotech.fintrack.domain.model.TransactionType
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDTO(

    var id: Long? = null,

    @field:NotNull(message = "Não pode ser nulo")
    var transactionType: TransactionType? = null,

    @field:NotNull(message = "Não pode ser nulo")
    var category: ExpenseCategory? = null,

    @field:NotNull(message = "Não pode ser nulo")
    var bank: BankAccount? = null,

    @field:NotNull(message = "Não pode ser nulo")
    @field:Positive(message = "O valor da transação deve ser maior que zero")
    var amount: BigDecimal? = null,

    @field:PastOrPresent(message = "A data não pode estar no futuro")
    var transactionDate: LocalDateTime? = null,

    var notified: Boolean = false,

    @field:PastOrPresent(message = "A data não pode estar no futuro")
    var createdAt: LocalDateTime? = null
) {
    constructor() : this(
        id = null,
        transactionType = null,
        category = null,
        bank = null,
        amount = null,
        transactionDate = null,
        notified = false,
        createdAt = null
    )
}