package com.lagotech.fintrack.service

import com.lagotech.fintrack.adapters.outbound.repository.TransactionRepository
import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.application.mapper.EntityToDTOMapperImpl
import com.lagotech.fintrack.domain.model.Transaction
import com.lagotech.fintrack.domain.service.TransactionService
import com.lagotech.fintrack.mocks.BankAccountMock
import com.lagotech.fintrack.mocks.ExpenseCategoryMock
import com.lagotech.fintrack.mocks.TransactionMock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class TransactionServiceTest {

    @Mock
    private lateinit var service: TransactionService

    @Mock
    private lateinit var repository: TransactionRepository

    private val entityToDTOMapper: EntityToDTOMapper = EntityToDTOMapperImpl()

    lateinit var bankMock: BankAccountMock
    lateinit var categoryMock: ExpenseCategoryMock
    lateinit var transactionMock: TransactionMock

    @BeforeEach
    fun setUp() {
        bankMock = BankAccountMock(entityToDTOMapper)
        categoryMock = ExpenseCategoryMock(entityToDTOMapper)
        transactionMock = TransactionMock(entityToDTOMapper, categoryMock, bankMock)
        service = TransactionService(repository, entityToDTOMapper)
    }

    @Test
    fun save() {
        val expectedDTO = transactionMock.getTransactionDTO()

        val entity = entityToDTOMapper.parseObject(expectedDTO, Transaction::class.java)

        `when`(repository.save(entity)).thenReturn(entity)

        val result = service.save(expectedDTO)

        assertNotNull(result)
        assertNotNull(entity.id)
        assertEquals(entity.category.name, result.category?.name)
        assertEquals(entity.bank.bankName, result.bank?.bankName)

    }

    @Test
    fun findById() {
        val id = 1L
        val entity = transactionMock.getTransaction()
        val expectedDTO = entityToDTOMapper.parseObject(entity, TransactionDTO::class.java)

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))

        val result = service.findById(id)

        assertNotNull(result)
        assertEquals(expectedDTO.bank, result.bank)
    }

    @Test
    fun findById_ShoudReturnException_WhenNotFoundById() {
        val id = 0L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        val exception = assertThrows<ResourceNotFoundException> {
            service.findById(id)
        }

        assertEquals("Transaction with id $id not found", exception.message)
    }

    @Test
    fun findAll() {
        val transactions = transactionMock.getTransactionList()
        val expectedTransactionsList = transactionMock.getTransactionDTOList()

        `when`(repository.findAll()).thenReturn(transactions)

        val result = service.findAll()
        assertNotNull(result)
    }

    @Test
    fun findAll_ShouldReturnException_WhenNotFoundResources() {

        `when`(repository.findAll()).thenReturn(emptyList())

        val exception = assertThrows<ResourceNotFoundException> {
            service.findAll()
        }

        assertEquals("Recurso não encontrado", exception.message)
    }
}