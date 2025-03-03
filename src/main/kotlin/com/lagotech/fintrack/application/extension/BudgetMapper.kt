package com.lagotech.fintrack.application.extension

import com.lagotech.fintrack.adapters.inbound.controller.BudgetController
import com.lagotech.fintrack.application.request.BudgetRequest
import com.lagotech.fintrack.application.response.BudgetResponse
import com.lagotech.fintrack.domain.model.Budget
import com.lagotech.fintrack.domain.model.TransactionCategory
import com.lagotech.fintrack.domain.model.User
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

object BudgetMapper {

    fun toEntity(request: BudgetRequest, user: User, category: TransactionCategory): Budget {
        return Budget(
            budgetLimit = request.budgetLimit,
            startDate = request.startDate,
            endDate = request.endDate,
            user = user,
            category = category
        )
    }

    fun toResponse(budget: Budget): BudgetResponse {

        val response = BudgetResponse(
            id = budget.id,
            budgetLimit = budget.budgetLimit,
            startDate = budget.startDate,
            endDate = budget.endDate,
            userId = budget.user.id!!,
            categoryId = budget.category.id!!
        )

        response.add(linkTo(methodOn(BudgetController::class.java).findById(budget.id!!)).withSelfRel())

        response.add(linkTo(methodOn(BudgetController::class.java).findByUserId(budget.user.id!!)).withRel("userBudgets"))

        return response
    }

    fun toResponseList(budgets: List<Budget>): List<BudgetResponse> {
        return budgets.map { toResponse(it) }
    }
}
