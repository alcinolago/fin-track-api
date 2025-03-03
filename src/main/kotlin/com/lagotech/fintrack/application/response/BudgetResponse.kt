package com.lagotech.fintrack.application.response

import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import java.time.LocalDateTime

data class BudgetResponse(
    val id: Long?,
    val budgetLimit: BigDecimal,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val userId: Long,
    val categoryId: Long
) : RepresentationModel<BudgetResponse>()