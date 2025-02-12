package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.ExpenseCategoryRepository
import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.ExpenseCategory
import org.springframework.stereotype.Service

@Service
class ExpenseCategoryService(
    private val respository: ExpenseCategoryRepository,
    private val entityToDTOMapper: EntityToDTOMapper
) {

    fun save(expenseCategory: ExpenseCategory): ExpenseCategoryDTO {
        return entityToDTOMapper.parseObject(respository.save(expenseCategory), ExpenseCategoryDTO::class.java)
    }

    fun findByName(name: String): List<ExpenseCategoryDTO> {
        return respository.findByNameContaining(name)
            .map { entityToDTOMapper.parseObject(it, ExpenseCategoryDTO::class.java) }
    }

    fun findAll(): List<ExpenseCategoryDTO> {
        return respository.findAll().let { entityToDTOMapper.parseListObjects(it, ExpenseCategoryDTO::class.java) }
    }

    fun findById(id: Long): ExpenseCategoryDTO {
        val expenseCategory = respository.findById(id)
            .orElseThrow { IllegalArgumentException("Expense category with id $id not found") }

        return entityToDTOMapper.parseObject(expenseCategory, ExpenseCategoryDTO::class.java)
    }

    fun existsByName(name: String): Boolean {
        return respository.existsByName(name)
    }

    fun delete(expenseCategory: ExpenseCategory) {
        respository.delete(expenseCategory)
    }
}