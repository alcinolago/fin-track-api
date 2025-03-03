package com.lagotech.fintrack.mocks

import com.lagotech.fintrack.application.dto.TransactionCategoryDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.TransactionCategory
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ExpenseCategoryMock(private val entityToDTOMapper: EntityToDTOMapper) {

    fun mockExpenseCategoryDTO(): TransactionCategoryDTO {
        return TransactionCategoryDTO(1, "Food", "Food expenses", "#0000ff", LocalDateTime.of(2025, 2, 25, 14, 30))
    }

    fun mockExpenseCategoryDTOList(): List<TransactionCategoryDTO> {
        return listOf(
            TransactionCategoryDTO(1, "Food", "Food expenses", "#0000ff", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0)),
            TransactionCategoryDTO(
                2,
                "Transport",
                "Transport expenses",
                "#ff0000",
                LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0)
            )
        )
    }

//    fun mockExpenseCategory(): TransactionCategory {
//        return TransactionCategory(1, "Food", "Food expenses", "#0000ff", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0))
//    }
//
//    fun mockExpenseCategoryList(): List<TransactionCategory> {
//        return listOf(
//            TransactionCategory(1, "Food", "Food expenses", "#0000ff", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0)),
//            TransactionCategory(
//                2,
//                "Transport",
//                "Transport expenses",
//                "#ff0000",
//                LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0)
//            )
//        )
//    }

//    fun mockEntityToDTO(): TransactionCategoryDTO {
//        return entityToDTOMapper.parseObject(mockExpenseCategory(), TransactionCategoryDTO::class.java)
//    }

    fun mockDTOToEntity(): TransactionCategory {
        return entityToDTOMapper.parseObject(mockExpenseCategoryDTO(), TransactionCategory::class.java)
    }

//    fun mockEntityListToDTOList(): List<TransactionCategoryDTO> {
//        return entityToDTOMapper.parseListObjects(mockExpenseCategoryList(), TransactionCategoryDTO::class.java)
//    }

    fun mockDTOListToEntityList(): List<TransactionCategory> {
        return entityToDTOMapper.parseListObjects(mockExpenseCategoryDTOList(), TransactionCategory::class.java)
    }
}