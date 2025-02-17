package com.lagotech.fintrack.service

import com.lagotech.fintrack.adapters.outbound.repository.TransactionRepository
import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.application.mapper.EntityToDTOMapperImpl
import com.lagotech.fintrack.domain.model.Transaction
import com.lagotech.fintrack.domain.service.TransactionService
import com.lagotech.fintrack.mocks.TransactionMock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.context.MessageSource
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class TransactionServiceTest {

    @Mock
    private lateinit var service: TransactionService

    @Mock
    private lateinit var repository: TransactionRepository

    @Mock
    private lateinit var messageSource: MessageSource

    private val entityToDTOMapper: EntityToDTOMapper = EntityToDTOMapperImpl()

    private lateinit var transactionMock: TransactionMock

    @BeforeEach
    fun setUp() {
        transactionMock = TransactionMock(entityToDTOMapper)
        service = TransactionService(repository, entityToDTOMapper, messageSource)
    }

    @Test
    fun `should save the transaction when all requirements are correct`() {
        val expectedDTO = transactionMock.getTransactionDTO()

        val entity = entityToDTOMapper.parseObject(expectedDTO, Transaction::class.java)

        `when`(repository.save(entity)).thenReturn(entity)

        val result = service.save(expectedDTO)

        assertNotNull(result)
        assertNotNull(entity.id)
    }

    @Test
    fun `should return a transaction when id is valid`() {
        val id = 1L
        val entity = transactionMock.getTransaction()
        val expectedDTO = entityToDTOMapper.parseObject(entity, TransactionDTO::class.java)

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))

        val result = service.findById(id)

        assertNotNull(result)
    }

    @Test
    fun `should return an exception when id transaction not exists`() {
        val id = 0L

        `when`(repository.findById(id)).thenReturn(Optional.empty())
        `when`(messageSource.getMessage(anyString(), any(), any()))
            .thenReturn("Entidade com id $id não encontrada")

        val exception = assertThrows<ResourceNotFoundException> {
            service.findById(id)
        }

        assertEquals("Entidade com id $id não encontrada", exception.message)
    }

    @Test
    fun `should return all transactions`() {
        val transactions = transactionMock.getTransactionList()
        val expectedTransactionsList = transactionMock.getTransactionDTOList()

        `when`(repository.findAllWithDetails()).thenReturn(transactions)

        val result = service.findAll()
        assertNotNull(result)
    }

    @Test
    fun `should return an exception when not found any transaction`() {

        `when`(repository.findAllWithDetails()).thenReturn(emptyList())
        `when`(messageSource.getMessage(anyString(), any(), any()))
            .thenReturn("Recurso não encontrado")

        val exception = assertThrows<ResourceNotFoundException> {
            service.findAll()
        }

        assertEquals("Recurso não encontrado", exception.message)
    }
/*
    @Test
    fun `should update transaction successfully when id is valid`() {
        val id = 1L
        val entity = transactionMock.getTransaction()
        val expectedDTO = entityToDTOMapper.parseObject(entity, TransactionDTO::class.java)

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(entity)

        val result = service.update(id, expectedDTO)

        assertNotNull(result)
    }

    @Test
    fun `should thrown an exception on update when transaction id not exists`() {
        val id = 99L
        val entity = transactionMock.getTransaction()
        val expectedDTO = entityToDTOMapper.parseObject(entity, TransactionDTO::class.java)

        `when`(repository.findById(id)).thenReturn(Optional.empty())
        `when`(messageSource.getMessage(anyString(), any(), any()))
            .thenReturn("Entidade com id $id não encontrada")

        val exception = assertThrows<ResourceNotFoundException> {
            service.update(id, expectedDTO)
        }

        assertEquals("Entidade com id $id não encontrada", exception.message)
    }
*/
    @Test
    fun `should thrown an exception on delete when transaction id not exists`() {
        val id = 99L

        `when`(repository.findById(id)).thenReturn(Optional.empty())
        `when`(messageSource.getMessage(anyString(), any(), any()))
            .thenReturn("Entidade com id $id não encontrada")


        val exception = assertThrows<ResourceNotFoundException> {
            service.delete(id)
        }

        assertEquals("Entidade com id $id não encontrada", exception.message)
    }
}