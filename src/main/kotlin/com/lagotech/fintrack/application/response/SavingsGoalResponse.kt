package com.lagotech.fintrack.application.response

import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import java.time.LocalDateTime

data class SavingsGoalResponse(
    val id: Long,
    val userId: Long,
    val goalName: String,
    val targetAmount: BigDecimal,
    val currentAmount: BigDecimal,
    val targetDate: LocalDateTime?
) : RepresentationModel<SavingsGoalResponse>()