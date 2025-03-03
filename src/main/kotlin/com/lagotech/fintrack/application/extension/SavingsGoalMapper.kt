package com.lagotech.fintrack.application.extension

import com.lagotech.fintrack.adapters.inbound.controller.SavingsGoalController
import com.lagotech.fintrack.application.request.SavingsGoalRequest
import com.lagotech.fintrack.application.response.SavingsGoalResponse
import com.lagotech.fintrack.domain.model.SavingsGoal
import com.lagotech.fintrack.domain.model.User
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

object SavingsGoalMapper {

    fun toEntity(request: SavingsGoalRequest, user: User): SavingsGoal {
        return SavingsGoal(
            user = user,
            goalName = request.goalName,
            targetAmount = request.targetAmount,
            currentAmount = request.currentAmount,
            targetDate = request.targetDate
        )
    }

    fun toResponse(savingsGoal: SavingsGoal): SavingsGoalResponse {
        val response = SavingsGoalResponse(
            id = savingsGoal.id!!,
            userId = savingsGoal.user.id!!,
            goalName = savingsGoal.goalName,
            targetAmount = savingsGoal.targetAmount,
            currentAmount = savingsGoal.currentAmount,
            targetDate = savingsGoal.targetDate
        )

        response.add(linkTo(methodOn(SavingsGoalController::class.java).findById(savingsGoal.id!!)).withSelfRel())

        response.add(linkTo(methodOn(SavingsGoalController::class.java).findByUserId(savingsGoal.user.id!!)).withRel("userSavingsGoal"))

        return response
    }

    fun toResponseList(savings: List<SavingsGoal>): List<SavingsGoalResponse> {
        return savings.map { toResponse(it) }
    }
}