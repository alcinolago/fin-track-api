package com.lagotech.fintrack.domain.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    var transactionType: TransactionType,

    //@Column(name = "category_id", nullable = false)
    //var categoryId: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    var category: ExpenseCategory,

    //@Column(name = "bank_id", nullable = false)
    //var bankId: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id", nullable = false)
    var bankAccount: BankAccount,

    @Column(name = "amount", nullable = false)
    var amount: BigDecimal,

    @Column(name = "transaction_date", nullable = false)
    var transactionDate: LocalDateTime,

    @Column(nullable = false)
    var notified: Boolean = false,

    @Column(name = "created_at", nullable = false)
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