package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.ExpenseCategoryRepository
import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.ExpenseCategory
import org.springframework.stereotype.Service

@Service
class ExpenseCategoryService(
    private val repository: ExpenseCategoryRepository,
    private val entityToDTOMapper: EntityToDTOMapper
) {

    fun save(expenseCategory: ExpenseCategory): ExpenseCategoryDTO {

        if (existsByName(expenseCategory.name)) {
            throw ResourceNotFoundException("Categoria com o nome ${expenseCategory.name} já existe")
        }
        val savedExpenseCategory = repository.save(expenseCategory)

        return entityToDTOMapper.parseObject(savedExpenseCategory, ExpenseCategoryDTO::class.java)
    }

    fun findByName(name: String): List<ExpenseCategoryDTO> {

        val expenseCategory = repository.findByNameContaining(name)

        if (expenseCategory.isEmpty()) {
            throw ResourceNotFoundException("Recurso não encontrado")
        }

        return entityToDTOMapper.parseListObjects(expenseCategory, ExpenseCategoryDTO::class.java)
    }

    fun findAll(): List<ExpenseCategoryDTO> {

        val expenseCategoryList = repository.findAll()

        if (expenseCategoryList.isEmpty()) {
            throw ResourceNotFoundException("Recurso não encontrado")
        }
        return entityToDTOMapper.parseListObjects(expenseCategoryList, ExpenseCategoryDTO::class.java)
    }

    fun findById(id: Long): ExpenseCategoryDTO {
        val expenseCategory = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Expense category with id $id not found") }

        return entityToDTOMapper.parseObject(expenseCategory, ExpenseCategoryDTO::class.java)
    }

    fun existsByName(name: String): Boolean {
        return repository.existsByName(name)
    }

    fun delete(expenseCategory: ExpenseCategory) {
        repository.delete(expenseCategory)
    }
}