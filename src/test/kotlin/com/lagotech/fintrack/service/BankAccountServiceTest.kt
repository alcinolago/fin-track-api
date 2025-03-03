package com.lagotech.fintrack.service

import com.lagotech.fintrack.adapters.outbound.repository.BankAccountRepository
import com.lagotech.fintrack.application.dto.BankAccountDTO
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
class BankAccountServiceTest {

    /*@Mock
    private lateinit var service: BankAccountService

    @Mock
    private lateinit var repository: BankAccountRepository

    @Mock
    private lateinit var messageSource: MessageSource

    private val entityToDTOMapper: EntityToDTOMapper = EntityToDTOMapperImpl()

    private lateinit var mockBankAccount: BankAccountMock

    @BeforeEach
    fun setUp() {
        mockBankAccount = BankAccountMock(entityToDTOMapper)
        service = BankAccountService(repository, entityToDTOMapper, messageSource)
    }

    @Test
    fun `should save the transaction when all requirements are correct`() {
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
    fun `should return an exception when account bank already exists`() {
        val bankAccount = mockBankAccount.mockBankAccountDTO()

        `when`(repository.existsByBankName(bankAccount.bankName)).thenReturn(true)
        `when`(messageSource.getMessage(anyString(), any(), any()))
            .thenReturn("Já existe um recurso com o valor informado: ${bankAccount.bankName}")

        val exception = assertThrows<IllegalArgumentException> {
            service.save(bankAccount)
        }

        assertEquals("Já existe um recurso com o valor informado: ${bankAccount.bankName}", exception.message)
    }

    @Test
    fun `should return a account bank when category name exists`() {
        val bankName = "Itau"
        val bankAccountList = mockBankAccount.mockBankAccountDTOList()

        `when`(repository.findByBankNameContaining(bankName)).thenReturn(bankAccountList)

        val result = service.findByName(bankName)
        assertNotNull(result)
    }

    @Test
    fun `should return an exception when category name not exists`() {
        val bankName = ""
        val bankAccountList = emptyList<BankAccountDTO>()

        `when`(repository.findByBankNameContaining(bankName)).thenReturn(bankAccountList)
        `when`(messageSource.getMessage(anyString(), any(), any()))
            .thenReturn("Recurso não encontrado")


        val exception = assertThrows<ResourceNotFoundException> {
            service.findByName(bankName)
        }
        assertEquals("Recurso não encontrado", exception.message)
    }

    @Test
    fun `should return all account bank`() {
        val bankAccountList = mockBankAccount.mockBankAccountList()

        `when`(repository.findAll()).thenReturn(bankAccountList)

        val result = service.findAll()
        assertNotNull(result)
    }

    @Test
    fun `should return an exception when there is no account bank`() {
        val bankAccountList = emptyList<BankAccount>()

        `when`(repository.findAll()).thenReturn(bankAccountList)
        `when`(messageSource.getMessage(anyString(), any(), any()))
            .thenReturn("Recurso não encontrado")

        val exception = assertThrows<ResourceNotFoundException> {
            service.findAll()
        }

        assertEquals("Recurso não encontrado", exception.message)
    }

    @Test
    fun `should return a category when account bank id exists`() {
        val id = 1L
        val entity = mockBankAccount.mockBankAccount()
        val expectedDTO: Optional<BankAccountDTO> =
            Optional.of(entityToDTOMapper.parseObject(entity, BankAccountDTO::class.java))

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))

        val result = service.findById(id)

        assertNotNull(result)
        assertEquals(expectedDTO, result)
    }

    @Test
    fun `should return true when category exists by name`() {
        val bankName = "Itau"

        `when`(repository.existsByBankName(bankName)).thenReturn(true)

        val result = service.existsByBankName(bankName)
        assertNotNull(result)
    }

    @Test
    fun `should return false when category not exists by name`() {
        val bankName = "NotExists"

        `when`(repository.existsByBankName(bankName)).thenReturn(false)

        val result = service.existsByBankName(bankName)

        assertEquals(false, result)
    }

    @Test
    fun `should update successfully`() {
        val id = 1L
        val entity = mockBankAccount.mockBankAccount()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(entity)

        val result = service.update(id, entityToDTOMapper.parseObject(entity, BankAccountDTO::class.java))

        assertNotNull(result)
    }

    @Test
    fun `should thrown an exception on update when id not exists`() {
        val id = 99L
        val expectedDTO = mockBankAccount.mockBankAccountDTO()

        `when`(repository.findById(id)).thenReturn(Optional.empty())
        `when`(messageSource.getMessage(anyString(), any(), any()))
            .thenReturn("Entidade com id $id não encontrada")

        val exception = assertThrows<ResourceNotFoundException> {
            service.update(id, expectedDTO)
        }

        assertEquals("Entidade com id $id não encontrada", exception.message)
    }

    @Test
    fun `should thrown an exception on delete when id not exists`() {
        val id = 99L
        val expectedDTO = mockBankAccount.mockBankAccountDTO()

        `when`(repository.findById(id)).thenReturn(Optional.empty())
        `when`(messageSource.getMessage(anyString(), any(), any()))
            .thenReturn("Entidade com id $id não encontrada")

        val exception = assertThrows<ResourceNotFoundException> {
            service.delete(id)
        }

        assertEquals("Entidade com id $id não encontrada", exception.message)
    }*/
}