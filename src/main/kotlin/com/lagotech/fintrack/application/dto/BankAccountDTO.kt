package com.lagotech.fintrack.application.dto

import java.time.LocalDateTime

data class BankAccountDTO(
    var id: Long = 0,
    var bankName: String,
    var accountNumber: String,
    var accountDigit: String,
    var agency: String,
    var createdAt: LocalDateTime
) {
    constructor() : this(0, "", "", "", "", LocalDateTime.now())
}