package com.lagotech.fintrack.mocks

import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.ExpenseCategory
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ExpenseCategoryMock(private val entityToDTOMapper: EntityToDTOMapper) {

    fun mockExpenseCategoryDTO(): ExpenseCategoryDTO {
        return ExpenseCategoryDTO(1, "Food", "Food expenses", "Blue", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0))
    }

    fun mockExpenseCategoryDTOList(): List<ExpenseCategoryDTO> {
        return listOf(
            ExpenseCategoryDTO(1, "Food", "Food expenses", "Blue", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0)),
            ExpenseCategoryDTO(2, "Transport", "Transport expenses", "Red", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0))
        )
    }

    fun mockExpenseCategory(): ExpenseCategory {
        return ExpenseCategory(1, "Food", "Food expenses", "Blue", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0))
    }

    fun mockExpenseCategoryList(): List<ExpenseCategory> {
        return listOf(
            ExpenseCategory(1, "Food", "Food expenses", "Blue", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0)),
            ExpenseCategory(2, "Transport", "Transport expenses", "Red", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0))
        )
    }

    fun mockEntityToDTO(): ExpenseCategoryDTO {
        return entityToDTOMapper.parseObject(mockExpenseCategory(), ExpenseCategoryDTO::class.java)
    }

    fun mockDTOToEntity(): ExpenseCategory {
        return entityToDTOMapper.parseObject(mockExpenseCategoryDTO(), ExpenseCategory::class.java)
    }

    fun mockEntityListToDTOList(): List<ExpenseCategoryDTO> {
        return entityToDTOMapper.parseListObjects(mockExpenseCategoryList(), ExpenseCategoryDTO::class.java)
    }

    fun mockDTOListToEntityList(): List<ExpenseCategory> {
        return entityToDTOMapper.parseListObjects(mockExpenseCategoryDTOList(), ExpenseCategory::class.java)
    }

}