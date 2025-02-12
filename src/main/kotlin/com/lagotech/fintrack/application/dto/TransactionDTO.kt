package com.lagotech.fintrack.application.dto

import com.lagotech.fintrack.domain.model.BankAccount
import com.lagotech.fintrack.domain.model.ExpenseCategory
import com.lagotech.fintrack.domain.model.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDTO(
    var id: Long = 0,
    var transactionType: TransactionType,
    var category: ExpenseCategory,
    var bank: BankAccount,
    var amount: BigDecimal,
    var transactionDate: LocalDateTime,
    var notified: Boolean = false,
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