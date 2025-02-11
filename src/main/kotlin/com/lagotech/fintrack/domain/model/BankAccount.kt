package com.lagotech.fintrack.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import java.time.LocalDateTime

@Entity
@Table(name = "bank_accounts")
data class BankAccount(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "bank_name", nullable = false, length = 100)
    val bankName: String,

    @Column(name = "account_number", nullable = false, length = 20)
    val accountNumber: String,

    @Column(name = "account_digit", nullable = false, length = 5)
    val accountDigit: String,

    @Column(name = "agency", nullable = false, length = 10)
    val agency: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)