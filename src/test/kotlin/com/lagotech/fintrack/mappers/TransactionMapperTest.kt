package com.lagotech.fintrack.mappers

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class TransactionMapperTest {

//    private lateinit var entityToDTOMapper: EntityToDTOMapper
//
//    private lateinit var transactionMock: TransactionMock
//
//    @BeforeEach
//    fun setUp() {
//        entityToDTOMapper = EntityToDTOMapperImpl()
//        transactionMock = TransactionMock(entityToDTOMapper)
//    }
//
//    @Test
//    fun `should parse Transaction to TransactionDTO`() {
//
//        val transactionDTO = transactionMock.mockEntityToDTO()
//
//        val formattedTransactionDate = transactionDTO.transactionDate?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
//        val formattedCreatedAtDate = transactionDTO.createdAt?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
//
//        assertNotNull(transactionDTO)
//        assertEquals(1, transactionDTO.id)
//        assertEquals(TransactionType.EXPENSE, transactionDTO.transactionType)
//        assertEquals(BigDecimal(1000), transactionDTO.amount)
//        assertEquals("2025-02-25T14:30:00", formattedTransactionDate)
//        assertFalse(transactionDTO.notified)
//        assertEquals("2025-02-11T14:30:00", formattedCreatedAtDate)
//    }
//
//    @Test
//    fun `should parse TransactionDTO to Transaction`() {
//
//        val transaction = transactionMock.mockDTOToEntity()
//
//        val formattedTransactionDate = transaction.transactionDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
//        val formattedCreatedAtDate = transaction.createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
//
//        assertNotNull(transaction)
//        assertEquals(1, transaction.id)
//        assertEquals(TransactionType.EXPENSE, transaction.transactionType)
//        assertEquals(BigDecimal(1000), transaction.amount)
//        assertEquals("2025-02-25T14:30:00", formattedTransactionDate)
//        assertFalse(transaction.notified)
//        assertEquals("2025-02-11T14:30:00", formattedCreatedAtDate)
//    }
//
//    @Test
//    fun `should parse Transaction list to TransactionDTO list`() {
//
//        val transactionDTOList = transactionMock.mockEntityListToDTOList()
//
//        val formattedTransactionDate =
//            transactionDTOList[0].transactionDate?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
//        val formattedCreatedAtDate = transactionDTOList[0].createdAt?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
//
//        assertNotNull(transactionDTOList)
//        assertEquals(2, transactionDTOList.size)
//        assertEquals(1, transactionDTOList[0].id)
//        assertEquals(TransactionType.EXPENSE, transactionDTOList[0].transactionType)
//        assertEquals(BigDecimal(1000), transactionDTOList[0].amount)
//        assertEquals("2025-02-25T14:30:00", formattedTransactionDate)
//        assertFalse(transactionDTOList[0].notified)
//        assertEquals("2025-02-11T14:30:00", formattedCreatedAtDate)
//    }
//
//    @Test
//    fun `should parse TransactionDTO list to Transaction list`() {
//
//        val transactionList = transactionMock.mockDTOListToEntityList()
//
//        val formattedTransactionDate = transactionList[0].transactionDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
//        val formattedCreatedAtDate = transactionList[0].createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
//
//        assertNotNull(transactionList)
//        assertEquals(2, transactionList.size)
//        assertEquals(1, transactionList[0].id)
//        assertEquals(TransactionType.EXPENSE, transactionList[0].transactionType)
//        assertEquals(BigDecimal(1000), transactionList[0].amount)
//        assertEquals("2025-02-25T14:30:00", formattedTransactionDate)
//        assertFalse(transactionList[0].notified)
//        assertEquals("2025-02-11T14:30:00", formattedCreatedAtDate)
//    }
}