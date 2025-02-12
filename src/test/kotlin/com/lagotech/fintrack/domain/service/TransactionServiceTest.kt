package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.ExpenseCategoryRepository
import com.lagotech.fintrack.adapters.outbound.repository.TransactionRepository
import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.Transaction
import com.lagotech.fintrack.mocks.BankAccountMock
import com.lagotech.fintrack.mocks.ExpenseCategoryMock
import com.lagotech.fintrack.mocks.TransactionMock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class TransactionServiceTest {

    @InjectMocks
    private lateinit var service: TransactionService

    @Mock
    private lateinit var repository: TransactionRepository

    @Mock
    private lateinit var entityToDTOMapper: EntityToDTOMapper

    lateinit var bankMock: BankAccountMock
    lateinit var categoryMock: ExpenseCategoryMock
    lateinit var transactionMock: TransactionMock

    @BeforeEach
    fun setUp() {
        bankMock = BankAccountMock(entityToDTOMapper)
        categoryMock = ExpenseCategoryMock(entityToDTOMapper)
        transactionMock = TransactionMock(entityToDTOMapper,categoryMock,bankMock)
    }

    @Test
    fun save() {
        val transaction = transactionMock.getTransaction()
        val expectedDTO = transactionMock.getTransactionDTO()

        `when`(entityToDTOMapper.parseObject(transaction, TransactionDTO::class.java)).thenReturn(expectedDTO)
        `when`(repository.save(transaction)).thenReturn(transaction)

        val result = service.save(transaction)
        assertNotNull(result)
    }

    @Test
    fun findById() {
        val id = 1L
        val entity = transactionMock.getTransaction()
        val expectedDTO = transactionMock.getTransactionDTO()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(entityToDTOMapper.parseObject(entity,TransactionDTO::class.java)).thenReturn(expectedDTO)

        val result = service.findById(id)
        assertNotNull(result)
        assertEquals(expectedDTO.bank, result.bank)
    }

    @Test
    fun findAll() {
        val transactions = transactionMock.getTransactionList()
        val expectedTransactionsList = transactionMock.getTransactionDTOList()

        `when`(repository.findAll()).thenReturn(transactions)
        `when`(entityToDTOMapper.parseListObjects(transactions, TransactionDTO::class.java)).thenReturn(expectedTransactionsList)

        val result = service.findAll()
        assertNotNull(result)
    }
}