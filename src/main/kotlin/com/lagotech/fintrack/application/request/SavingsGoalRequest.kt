package com.lagotech.fintrack.application.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.LocalDateTime

data class SavingsGoalRequest(

    @Schema(description = "Identificador do usuário associado à meta de poupança", example = "1")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:Positive(message = "{generic.validation.positive}")
    val userId: Long,

    @Schema(description = "Nome da meta de poupança", example = "Viagem para Europa")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    val goalName: String,

    @Schema(description = "Valor total que se deseja alcançar", example = "10000.00")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:DecimalMin(value = "0.01", message = "{savingsGoal.targetAmount.minValue}")
    val targetAmount: BigDecimal,

    @Schema(description = "Valor atualmente poupado", example = "1500.00")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:DecimalMin(value = "0.00", message = "{savingsGoal.currentAmount.minValue}")
    val currentAmount: BigDecimal,

    @Schema(description = "Data limite para atingir a meta", example = "2025-12-31T23:59:59")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:FutureOrPresent(message = "{generic.validation.futureOrPresent}")
    val targetDate: LocalDateTime
)