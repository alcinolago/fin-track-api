package com.lagotech.fintrack.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "payable_accounts")
data class PayableAccount(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "account_name", nullable = false, length = 255)
    val accountName: String,

    @Column(name = "due_date", nullable = false)
    val dueDate: LocalDateTime,

    @Column(name = "amount", nullable = false)
    val amount: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: AccountStatus,
    @Column(name = "notified", nullable = false)
    val notified: Boolean = false,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)
