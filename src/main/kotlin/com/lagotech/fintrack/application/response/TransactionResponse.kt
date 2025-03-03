package com.lagotech.fintrack.application.response

import com.lagotech.fintrack.domain.type.TransactionStatus
import com.lagotech.fintrack.domain.type.TransactionType
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionResponse(
    val id: Long?,
    val type: TransactionType,
    val bankAccountId: Long,
    val amount: BigDecimal,
    val dueDate: LocalDateTime?,
    val status: TransactionStatus,
) : RepresentationModel<TransactionResponse>()