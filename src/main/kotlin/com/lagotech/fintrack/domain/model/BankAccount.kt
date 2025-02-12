package com.lagotech.fintrack.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "bank_accounts")
data class BankAccount(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "bank_name", nullable = false, length = 100)
    var bankName: String,

    @Column(name = "account_number", nullable = false, length = 20)
    var accountNumber: String,

    @Column(name = "account_digit", nullable = false, length = 5)
    var accountDigit: String,

    @Column(name = "agency", nullable = false, length = 10)
    var agency: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(0, "", "", "", "", LocalDateTime.now())
}