package com.lagotech.fintrack.application.dto

import com.lagotech.fintrack.domain.model.TransactionType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import java.time.LocalDateTime

@Schema(
    hidden = true,
    contentMediaType = "application/json",
    example = """{
        "transactionType": "EXPENSE",
        "categoryId": 1,
        "bankId": 11,
        "amount": 99.50,
        "transactionDate": "2025-02-13T14:44:00",
        "notified": false,
        "createdAt": "2025-02-13T14:44:00"
    }"""
)
data class TransactionDTO(

    @Schema(description = "Identificador único da transação", example = "1")
    var id: Long? = null,

    @Schema(description = "Tipo da transação (INCOME ou EXPENSE)", example = "EXPENSE")
    @field:NotNull(message = "{generic.validation.notNull}")
    var transactionType: TransactionType? = null,

    @Schema(description = "ID da categoria da despesa", example = "10")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:Min(1, message = "{generic.validation.id}")
    var categoryId: Long = 0,

    @Schema(description = "ID da conta bancária associada", example = "5")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:Min(1, message = "{generic.validation.id}")
    var bankId: Long = 0,

    @Schema(description = "Valor da transação", example = "150.75")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:Positive(message = "{generic.validation.positive}")
    var amount: BigDecimal? = null,

    @Schema(description = "Data da transação", example = "2025-02-14T10:30:00")
    @field:PastOrPresent(message = "{generic.validation.createdAt.pastOrPresent}")
    var transactionDate: LocalDateTime? = null,

    @Schema(description = "Indica se foi notificado", example = "false")
    var notified: Boolean = false,

    @Schema(description = "Data de criação do registro", example = "2025-02-14T14:00:00")
    @field:PastOrPresent(message = "{generic.validation.createdAt.pastOrPresent}")
    var createdAt: LocalDateTime? = null

) : RepresentationModel<TransactionDTO>() {
    constructor() : this(
        id = null,
        transactionType = TransactionType.EXPENSE,
        categoryId = 0,
        bankId = 0,
        amount = BigDecimal.ZERO,
        transactionDate = LocalDateTime.now(),
        notified = false,
        createdAt = LocalDateTime.now()
    )
}