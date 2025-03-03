package com.lagotech.fintrack.application.response

import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal

class BankAccountResponse(
    val id: Long?,
    val bankName: String,
    val accountNumber: String,
    val accountDigit: String,
    val agency: String,
    val balance: BigDecimal,
    val userId: Long
) : RepresentationModel<BankAccountResponse>()