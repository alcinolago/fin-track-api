package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.SavingsGoalRepository
import com.lagotech.fintrack.application.extension.SavingsGoalMapper
import com.lagotech.fintrack.application.response.SavingsGoalResponse
import com.lagotech.fintrack.domain.model.SavingsGoal
import org.springframework.stereotype.Service
import java.util.*

@Service
class SavingsGoalService(private val repository: SavingsGoalRepository) {

    fun createSavingGoal(req: SavingsGoal): SavingsGoalResponse {
        return SavingsGoalMapper.toResponse(repository.save(req))
    }

    fun findById(savingsId: Long): Optional<SavingsGoal> {
        return repository.findById(savingsId)
    }

    fun findByUserId(userId: Long): List<SavingsGoal> {
        return repository.findByUserId(userId)
    }

    fun updateSavingsGoal(savingsGoal: SavingsGoal): SavingsGoal {
        return repository.save(savingsGoal)
    }

    fun deleteSavingsGoal(savings: SavingsGoal) {
        repository.delete(savings)
    }
}