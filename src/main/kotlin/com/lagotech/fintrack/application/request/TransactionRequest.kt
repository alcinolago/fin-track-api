package com.lagotech.fintrack.application.request

import com.lagotech.fintrack.domain.type.TransactionStatus
import com.lagotech.fintrack.domain.type.TransactionType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionRequest(

    @Schema(description = "Tipo da transação (INCOME ou EXPENSE)", example = "EXPENSE")
    @field:NotNull(message = "{generic.validation.notNull}")
    val type: TransactionType,

    @Schema(description = "ID da conta bancária associada", example = "5")
    @field:NotNull(message = "{generic.validation.notNull}")
    val bankAccountId: Long,

    @Schema(description = "Valor da transação", example = "150.75")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:Positive(message = "{generic.validation.positive}")
    val amount: BigDecimal,

    @Schema(description = "Data de vencimento da transação (opcional)", example = "2025-05-15T10:30:00")
    val dueDate: LocalDateTime? = null,

    @Schema(description = "Status da transação (PAID ou UNPAID)", example = "PAID")
    @field:NotNull(message = "{generic.validation.notNull}")
    val status: TransactionStatus = TransactionStatus.PAID
)