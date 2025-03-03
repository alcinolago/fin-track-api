package com.lagotech.fintrack.domain.model

import com.lagotech.fintrack.domain.type.TransactionStatus
import com.lagotech.fintrack.domain.type.TransactionType
import jakarta.persistence.*
import org.hibernate.Hibernate
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_seq")
    @SequenceGenerator(name = "transactions_seq", sequenceName = "transactions_seq", allocationSize = 1)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id", nullable = false)
    var bankAccount: BankAccount,

    @Column(nullable = false)
    var amount: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: TransactionType,

    @Column(name = "due_date", nullable = true)
    var dueDate: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: TransactionStatus = TransactionStatus.PAID
) {
    constructor() : this(
        id = null,
        bankAccount = BankAccount(),
        amount = BigDecimal.ZERO,
        type = TransactionType.EXPENSE,
        dueDate = null,
        status = TransactionStatus.PAID
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Transaction
        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String {
        return "Transaction(id=$id, bankAccount=$bankAccount, amount=$amount, type=$type, dueDate=$dueDate, status=$status)"
    }
}