package com.lagotech.fintrack.mappers

import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.ExpenseCategory
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ExpenseCategoryMock(private val entityToDTOMapper: EntityToDTOMapper) {

    fun mockExpenseCategoryDTO(): ExpenseCategoryDTO {
        return ExpenseCategoryDTO(1, "Food", "Food expenses", "Blue", LocalDateTime.now())
    }

    fun mockExpenseCategoryDTOList(): List<ExpenseCategoryDTO> {
        return listOf(
            ExpenseCategoryDTO(1, "Food", "Food expenses", "Blue", LocalDateTime.now()),
            ExpenseCategoryDTO(2, "Transport", "Transport expenses", "Red", LocalDateTime.now())
        )
    }

    fun mockExpenseCategory(): ExpenseCategory {
        return ExpenseCategory(1, "Food", "Food expenses", "Blue", LocalDateTime.now())
    }

    fun mockExpenseCategoryList(): List<ExpenseCategory> {
        return listOf(
            ExpenseCategory(1, "Food", "Food expenses", "Blue", LocalDateTime.now()),
            ExpenseCategory(2, "Transport", "Transport expenses", "Red", LocalDateTime.now())
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