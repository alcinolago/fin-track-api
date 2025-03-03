package com.lagotech.fintrack.mappers

import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.application.mapper.EntityToDTOMapperImpl
import com.lagotech.fintrack.mocks.ExpenseCategoryMock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class TransactionCategoryMapperTest {

    private lateinit var entityToDTOMapper: EntityToDTOMapper

    private lateinit var expenseCategoryMock: ExpenseCategoryMock

    @BeforeEach
    fun setUp() {
        entityToDTOMapper = EntityToDTOMapperImpl()
        expenseCategoryMock = ExpenseCategoryMock(entityToDTOMapper)
    }

//    @Test
//    fun `should parse ExpenseCategory to ExpenseCategoryDTO`() {
//
//        val expenseCategoryDTO = expenseCategoryMock.mockEntityToDTO()
//
//        assertNotNull(expenseCategoryDTO)
//        assertEquals(1, expenseCategoryDTO.id)
//        assertEquals("Food", expenseCategoryDTO.name)
//        assertEquals("Food expenses", expenseCategoryDTO.description)
//        assertEquals("#0000ff", expenseCategoryDTO.color)
//    }

    @Test
    fun `should parse ExpenseCategoryDTO to ExpenseCategory`() {
        val expenseCategory = expenseCategoryMock.mockDTOToEntity()

        assertNotNull(expenseCategory)
        assertEquals(1, expenseCategory.id)
        assertEquals("Food", expenseCategory.name)
        assertEquals("#0000ff", expenseCategory.color)
    }

//    @Test
//    fun `should parse ExpenseCategory List to ExpenseCategory DTO List`() {
//        val expenseCategoryDTOList = expenseCategoryMock.mockEntityListToDTOList()
//
//        assertNotNull(expenseCategoryDTOList)
//        assertEquals(2, expenseCategoryDTOList.size)
//        assertEquals(1, expenseCategoryDTOList[0].id)
//        assertEquals("Food", expenseCategoryDTOList[0].name)
//        assertEquals("Food expenses", expenseCategoryDTOList[0].description)
//        assertEquals("#0000ff", expenseCategoryDTOList[0].color)
//    }

    @Test
    fun `should parse ExpenseCategoryDTO List to ExpenseCategory List`() {
        val expenseCategoryList = expenseCategoryMock.mockDTOListToEntityList()

        assertNotNull(expenseCategoryList)
        assertEquals(2, expenseCategoryList.size)
        assertEquals(1, expenseCategoryList[0].id)
        assertEquals("Food", expenseCategoryList[0].name)
        assertEquals("#0000ff", expenseCategoryList[0].color)
    }
}