package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.ExpenseCategoryRepository
import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.mocks.ExpenseCategoryMock
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class ExpenseCategoryServiceTest {

    @InjectMocks
    private lateinit var service: ExpenseCategoryService

    @Mock
    private lateinit var repository: ExpenseCategoryRepository

    @Mock
    private lateinit var entityToDTOMapper: EntityToDTOMapper

    lateinit var mockExpenseCategory: ExpenseCategoryMock

    @BeforeEach
    fun setUp() {
        mockExpenseCategory = ExpenseCategoryMock(entityToDTOMapper)
    }

    @Test
    fun save() {
        val entity = mockExpenseCategory.mockExpenseCategory()
        val expectedDTO = mockExpenseCategory.mockExpenseCategoryDTO()

        `when`(entityToDTOMapper.parseObject(entity, ExpenseCategoryDTO::class.java)).thenReturn(expectedDTO)
        `when`(repository.save(entity)).thenReturn(entity)

        val result = service.save(entity)

        val formattedExpenseCategoryDate = result.createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        assertNotNull(result)
        assertNotNull(entity.id)
        assertEquals(entity.id, result.id)
        assertEquals(entity.name, result.name)
        assertEquals(entity.description, result.description)
        assertEquals(entity.color, result.color)
        assertEquals("2025-02-25T14:30:00", formattedExpenseCategoryDate)
    }

    @Test
    fun findByName() {
        val categoryName = "Food"
        val expenseCategoryList = mockExpenseCategory.mockExpenseCategoryList()

        `when`(repository.findByNameContaining(categoryName)).thenReturn(expenseCategoryList)

        val result = service.findByName(categoryName)

        assertNotNull(result)
    }

    @Test
    fun findAll() {
        val expenseCategoryList = mockExpenseCategory.mockExpenseCategoryList()

        `when`(repository.findAll()).thenReturn(expenseCategoryList)

        val result = service.findAll()

        assertNotNull(result)
    }

    @Test
    fun findById() {
        val id = 1L
        val entity = mockExpenseCategory.mockExpenseCategory()
        val expectedDTO = mockExpenseCategory.mockExpenseCategoryDTO()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(entityToDTOMapper.parseObject(entity, ExpenseCategoryDTO::class.java)).thenReturn(expectedDTO)

        val result = service.findById(id)

        assertNotNull(result)
        assertEquals(expectedDTO, result)
    }

    @Test
    fun existsByName() {
        val categoryName = "Food"

        `when`(repository.existsByName(categoryName)).thenReturn(true)

        val result = service.existsByName(categoryName)

        assertTrue(result)
    }

}