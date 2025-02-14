package com.lagotech.fintrack.service

import com.lagotech.fintrack.adapters.outbound.repository.BankAccountRepository
import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.application.mapper.EntityToDTOMapperImpl
import com.lagotech.fintrack.domain.model.BankAccount
import com.lagotech.fintrack.domain.service.BankAccountService
import com.lagotech.fintrack.mocks.BankAccountMock
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
class BankAccountServiceTest {

    @Mock
    private lateinit var service: BankAccountService

    @Mock
    private lateinit var repository: BankAccountRepository

    private val entityToDTOMapper: EntityToDTOMapper = EntityToDTOMapperImpl()

    private lateinit var mockBankAccount: BankAccountMock

    @BeforeEach
    fun setUp() {
        mockBankAccount = BankAccountMock(entityToDTOMapper)
        service = BankAccountService(repository, entityToDTOMapper)
    }

    @Test
    fun save() {
        val expectedDTO = mockBankAccount.mockBankAccountDTO()
        val entity = entityToDTOMapper.parseObject(expectedDTO, BankAccount::class.java)

        `when`(repository.save(entity)).thenReturn(entity)

        val result = service.save(expectedDTO)

        assertNotNull(result)
        assertNotNull(entity.id)
        assertEquals(entity.bankName, result.bankName)
        assertEquals(entity.accountNumber, result.accountNumber)
        assertEquals(entity.agency, result.agency)
    }

    @Test
    fun save_ShouldReturnException_WhenBankAlreadyExists() {
        val bankAccount = mockBankAccount.mockBankAccountDTO()

        `when`(repository.existsByBankName(bankAccount.bankName)).thenReturn(true)

        val exception = assertThrows<IllegalArgumentException> {
            service.save(bankAccount)
        }

        assertEquals("Banco com o nome ${bankAccount.bankName} já existe", exception.message)
    }

    @Test
    fun findByName() {
        val bankName = "Itau"
        val bankAccountList = mockBankAccount.mockBankAccountDTOList()

        `when`(repository.findByBankNameContaining(bankName)).thenReturn(bankAccountList)

        val result = service.findByName(bankName)
        assertNotNull(result)
    }

    @Test
    fun findByName_ShouldReturnException_WhenNotFound() {
        val bankName = ""
        val bankAccountList = emptyList<BankAccountDTO>()

        `when`(repository.findByBankNameContaining(bankName)).thenReturn(bankAccountList)


        val exception = assertThrows<ResourceNotFoundException> {
            service.findByName(bankName)
        }
        assertEquals("Recurso não encontrado", exception.message)
    }

    @Test
    fun findAll() {
        val bankAccountList = mockBankAccount.mockBankAccountList()

        `when`(repository.findAll()).thenReturn(bankAccountList)

        val result = service.findAll()
        assertNotNull(result)
    }

    @Test
    fun findAll_ShouldReturnException_WhenNotFoundResources() {
        val bankAccountList = emptyList<BankAccount>()

        `when`(repository.findAll()).thenReturn(bankAccountList)

        val exception = assertThrows<ResourceNotFoundException> {
            service.findAll()
        }

        assertEquals("Recurso não encontrado", exception.message)
    }

    @Test
    fun findById() {
        val id = 1L
        val entity = mockBankAccount.mockBankAccount()
        val expectedDTO: Optional<BankAccountDTO> = Optional.of(entityToDTOMapper.parseObject(entity, BankAccountDTO::class.java))

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))

        val result = service.findById(id)

        assertNotNull(result)
        assertEquals(expectedDTO, result)
    }

    @Test
    fun existsByBankName() {
        val bankName = "Itau"

        `when`(repository.existsByBankName(bankName)).thenReturn(true)

        val result = service.existsByBankName(bankName)
        assertNotNull(result)
    }

    @Test
    fun existsByBankName_ShouldReturnFalse_WhenBankDoesNotExist() {
        val bankName = "NotExists"

        `when`(repository.existsByBankName(bankName)).thenReturn(false)

        val result = service.existsByBankName(bankName)

        assertEquals(false, result)
    }

    //TODO UPDATE SUCCESS
    //TODO UPDATE EXCEPTION
    //TODO DELETE EXCEPTION ID NOT EXITS

}