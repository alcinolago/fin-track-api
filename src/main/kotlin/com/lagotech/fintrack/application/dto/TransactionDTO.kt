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

    @field:NotNull(message = "O tipo da transação não pode ser nulo")
    var transactionType: TransactionType,

    @field:NotNull(message = "A categoria não pode ser nula")
    var category: ExpenseCategory,

    @field:NotNull(message = "O banco não pode ser nulo")
    var bank: BankAccount,

    @field:NotNull(message = "O valor não pode ser nulo")
    @field:Positive(message = "O valor da transação deve ser maior que zero")
    var amount: BigDecimal,

    @field:PastOrPresent(message = "A data da transação não pode estar no futuro")
    var transactionDate: LocalDateTime = LocalDateTime.now(),

    var notified: Boolean = false,

    @field:PastOrPresent(message = "A data de criação não pode estar no futuro")
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(
        0,
        TransactionType.EXPENSE,
        ExpenseCategory(),
        BankAccount(),
        BigDecimal.ZERO,
        LocalDateTime.now(),
        false,
        LocalDateTime.now()
    )
}