package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.BudgetRepository
import com.lagotech.fintrack.application.extension.BudgetMapper
import com.lagotech.fintrack.application.response.BudgetResponse
import com.lagotech.fintrack.domain.model.Budget
import org.springframework.stereotype.Service
import java.util.*

@Service
class BudgetService(private val repository: BudgetRepository) {

    fun save(budget: Budget): Budget {
        return repository.save(budget)
    }

    fun findById(id: Long): Optional<Budget> {
        return repository.findById(id)
    }

    fun findByUserId(userId: Long): List<Budget> {
        return repository.findByUserId(userId)
    }

    fun update(budget: Budget): BudgetResponse {
        return BudgetMapper.toResponse(repository.save(budget))
    }

    fun delete(budget: Budget) {
        repository.delete(budget)
    }
}