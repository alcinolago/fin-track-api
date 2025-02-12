package com.lagotech.fintrack.mappers

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BankAccountMapperTest {

    @Autowired
    lateinit var bankAccountMock: BankAccountMock

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should parse BankAccount to BankAccountDTO`() {

        val bankAccountDTO = bankAccountMock.mockEntityToDTO()

        assertNotNull(bankAccountDTO)
        assertEquals(1, bankAccountDTO.id)
        assertEquals("Itau", bankAccountDTO.bankName)
        assertEquals("1234", bankAccountDTO.accountNumber)
        assertEquals("1", bankAccountDTO.accountDigit)
        assertEquals("0001", bankAccountDTO.agency)
    }

    @Test
    fun `should parse BankAccountDTO to BankAccount`() {
        val bankAccount = bankAccountMock.mockDTOToEntity()

        assertNotNull(bankAccount)
        assertEquals(1, bankAccount.id)
        assertEquals("Itau", bankAccount.bankName)
        assertEquals("1234", bankAccount.accountNumber)
        assertEquals("1", bankAccount.accountDigit)
        assertEquals("0001", bankAccount.agency)
    }

    @Test
    fun `should parse BankAccount List to Bank Acccount DTO List`() {
        val bankAccountDTOList = bankAccountMock.mockEntityListToDTOList()

        assertNotNull(bankAccountDTOList)
        assertEquals(2, bankAccountDTOList.size)
        assertEquals(1, bankAccountDTOList[0].id)
        assertEquals("Itau", bankAccountDTOList[0].bankName)
        assertEquals("1234", bankAccountDTOList[0].accountNumber)
        assertEquals("1", bankAccountDTOList[0].accountDigit)
        assertEquals("0001", bankAccountDTOList[0].agency)
        assertEquals(2, bankAccountDTOList[1].id)
        assertEquals("Bradesco", bankAccountDTOList[1].bankName)
        assertEquals("5678", bankAccountDTOList[1].accountNumber)
        assertEquals("2", bankAccountDTOList[1].accountDigit)
        assertEquals("5678", bankAccountDTOList[1].agency)
    }

    @Test
    fun `should parse BankAccountDTO List to Bank Account List`() {
        val bankAccountList = bankAccountMock.mockDTOListToEntityList()

        assertNotNull(bankAccountList)
        assertEquals(2, bankAccountList.size)
        assertEquals(1, bankAccountList[0].id)
        assertEquals("Itau", bankAccountList[0].bankName)
        assertEquals("1234", bankAccountList[0].accountNumber)
        assertEquals("1", bankAccountList[0].accountDigit)
        assertEquals("0001", bankAccountList[0].agency)
        assertEquals(2, bankAccountList[1].id)
        assertEquals("Bradesco", bankAccountList[1].bankName)
        assertEquals("5678", bankAccountList[1].accountNumber)
        assertEquals("2", bankAccountList[1].accountDigit)
        assertEquals("5678", bankAccountList[1].agency)
    }
}