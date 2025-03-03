package com.lagotech.fintrack.application.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.LocalDateTime

data class BudgetRequest(

    @Schema(description = "Identificador do usuário associado ao orçamento", example = "1")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:Positive(message = "{generic.validation.mustBePositive}")
    val userId: Long,

    @Schema(description = "Identificador da categoria do orçamento", example = "2")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:Positive(message = "{generic.validation.mustBePositive}")
    val categoryId: Long,

    @Schema(description = "Limite de gastos definido para o orçamento", example = "5000.00")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:DecimalMin(value = "0.01", message = "{budget.budgetLimit.minValue}")
    val budgetLimit: BigDecimal,

    @Schema(description = "Data de início do orçamento", example = "2025-01-01T00:00:00")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:PastOrPresent(message = "{budget.startDate.pastOrPresent}")
    val startDate: LocalDateTime,

    @Schema(description = "Data de término do orçamento", example = "2025-12-31T23:59:59")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:Future(message = "{budget.endDate.future}")
    val endDate: LocalDateTime
)