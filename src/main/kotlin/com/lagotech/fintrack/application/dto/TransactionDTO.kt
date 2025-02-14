package com.lagotech.fintrack.application.dto

import com.lagotech.fintrack.domain.model.TransactionType
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Positive
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDTO(

    var id: Long? = null,

    @field:NotNull(message = "{generic.validation.notNull}")
    var transactionType: TransactionType? = null,

    @field:NotNull(message = "{generic.validation.notNull}")
    var categoryId: Long = 0,

    @field:NotNull(message = "{generic.validation.notNull}")
    var bankId: Long = 0,

    @field:NotNull(message = "{generic.validation.notNull}")
    @field:Positive(message = "{generic.validation.positive}")
    var amount: BigDecimal? = null,

    @field:PastOrPresent(message = "{generic.validation.createdAt.pastOrPresent}")
    var transactionDate: LocalDateTime? = null,

    var notified: Boolean = false,

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