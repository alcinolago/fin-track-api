package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.BankAccountRepository
import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.mocks.BankAccountMock
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
class BankAccountServiceTest {

    @InjectMocks
    private lateinit var service: BankAccountService

    @Mock
    private lateinit var repository: BankAccountRepository

    @Mock
    private lateinit var entityToDTOMapper: EntityToDTOMapper

    private lateinit var mockBankAccount: BankAccountMock

    @BeforeEach
    fun setUp() {
        mockBankAccount = BankAccountMock(entityToDTOMapper)
    }

    @Test
    fun save() {
        val entity = mockBankAccount.mockBankAccount()
        val expectedDTO = mockBankAccount.mockBankAccountDTO()

        `when`(entityToDTOMapper.parseObject(entity, BankAccountDTO::class.java)).thenReturn(expectedDTO)
        `when`(repository.save(entity)).thenReturn(entity)

        val result = service.save(entity)
        assertNotNull(result)
    }

    @Test
    fun findByName() {
        val bankName = "Itau"
        val bankAccountList = mockBankAccount.mockBankAccountDTOList()

        `when`(repository.findByNameContaining(bankName)).thenReturn(bankAccountList)

        val result = service.findByName(bankName)
        assertNotNull(result)
    }

    @Test
    fun findAll() {
        val bankAccountList = mockBankAccount.mockBankAccountList()

        `when`(repository.findAll()).thenReturn(bankAccountList)

        val result = service.findAll()
        assertNotNull(result)
    }

    @Test
    fun findById() {
        val id = 1L
        val entity = mockBankAccount.mockBankAccount()
        val expectedDTO = mockBankAccount.mockBankAccountDTO()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(entityToDTOMapper.parseObject(entity, BankAccountDTO::class.java)).thenReturn(expectedDTO)

        val result = service.findById(id)

        assertNotNull(result)
        assertEquals(expectedDTO, result)
    }

    @Test
    fun existsByName() {
        val bankName = "Itau"

        `when`(repository.existsByName(bankName)).thenReturn(true)

        val result = service.existsByName(bankName)
        assertNotNull(result)
    }
}