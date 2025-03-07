package com.lagotech.fintrack.validation

import com.lagotech.fintrack.application.dto.BankAccountDTO
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime
import kotlin.test.assertFalse

@ExtendWith(MockitoExtension::class)
class BankAccountDTOValidationTest {

//    private lateinit var validator: Validator
//
//    @BeforeEach
//    fun setUp() {
//        val factory = Validation.buildDefaultValidatorFactory()
//        validator = factory.validator
//    }
//
//    @Test
//    fun `should failed When required fields isBlank`() {
//        val dto = BankAccountDTO(
//            bankName = "",
//            accountNumber = "",
//            accountDigit = "",
//            agency = "",
//            createdAt = LocalDateTime.now()
//        )
//
//        val violations = validator.validate(dto)
//
//        assertFalse(violations.isEmpty())
//
//        val blankViolations = violations.filter { it.messageTemplate.contains("{generic.validation.notBlank}") }
//
//        assertEquals(4, blankViolations.size)
//    }
//
//    @Test
//    fun `should failed When Account Number has less than 6 or more than 11 characters`() {
//        val dto = BankAccountDTO(
//            bankName = "Banco x",
//            accountNumber = "12345",
//            accountDigit = "1",
//            agency = "1234",
//            createdAt = LocalDateTime.now()
//        )
//
//        val violations = validator.validate(dto)
//
//        assertFalse(violations.isEmpty())
//
//        val sizeValidation = violations.filter { it.messageTemplate.contains("{bankAccount.accountNumber.size}") }
//        assertEquals(1, sizeValidation.size)
//    }
//
//    @Test
//    fun `should failed When AccountNumber is not a number`() {
//        val dto = BankAccountDTO(
//            bankName = "Banco x",
//            accountNumber = "abcdef",
//            accountDigit = "1",
//            agency = "1234",
//            createdAt = LocalDateTime.now()
//        )
//
//        val violations = validator.validate(dto)
//
//        assertFalse(violations.isEmpty())
//
//        val sizeValidation = violations.filter { it.messageTemplate.contains("{generic.validation.mustBeNumber}") }
//        assertEquals(1, sizeValidation.size)
//    }
//
//    @Test
//    fun `should failed When Account Digit has more than 1 character`() {
//        val dto = BankAccountDTO(
//            bankName = "Banco x",
//            accountNumber = "123456",
//            accountDigit = "12",
//            agency = "1234",
//            createdAt = LocalDateTime.now()
//        )
//
//        val violations = validator.validate(dto)
//
//        assertFalse(violations.isEmpty())
//
//        val sizeValidation = violations.filter { it.messageTemplate.contains("{bankAccount.accountDigit.size}") }
//        assertEquals(1, sizeValidation.size)
//    }
//
//    @Test
//    fun `should failed When Account Digit is not a number`() {
//        val dto = BankAccountDTO(
//            bankName = "Banco x",
//            accountNumber = "123456",
//            accountDigit = "a",
//            agency = "1234",
//            createdAt = LocalDateTime.now()
//        )
//
//        val violations = validator.validate(dto)
//
//        assertFalse(violations.isEmpty())
//
//        val sizeValidation = violations.filter { it.messageTemplate.contains("{generic.validation.mustBeNumber}") }
//        assertEquals(1, sizeValidation.size)
//    }
//
//    @Test
//    fun `should failed When Agency Digit has less than 4 or more than 4 characters`() {
//        val dto = BankAccountDTO(
//            bankName = "Banco x",
//            accountNumber = "123456",
//            accountDigit = "1",
//            agency = "123",
//            createdAt = LocalDateTime.now()
//        )
//
//        val violations = validator.validate(dto)
//
//        assertFalse(violations.isEmpty())
//
//        val sizeValidation = violations.filter { it.messageTemplate.contains("{bankAccount.agency.size}") }
//        assertEquals(1, sizeValidation.size)
//    }
//
//    @Test
//    fun `should fail When Agency Digit is not a number`() {
//        val dto = BankAccountDTO(
//            bankName = "Banco x",
//            accountNumber = "123456",
//            accountDigit = "1",
//            agency = "abcd",
//            createdAt = LocalDateTime.now()
//        )
//
//        val violations = validator.validate(dto)
//
//        assertFalse(violations.isEmpty())
//
//        val sizeValidation = violations.filter { it.messageTemplate.contains("{generic.validation.mustBeNumber}") }
//        assertEquals(1, sizeValidation.size)
//    }
//
//    @Test
//    fun `should fail when created at is on the Future`() {
//
//        val dtoWithFutureDate = BankAccountDTO(
//            bankName = "Banco Teste",
//            accountNumber = "123456",
//            accountDigit = "1",
//            agency = "1234",
//            createdAt = LocalDateTime.now().plusDays(1)
//        )
//
//        val violationsFutureDate = validator.validate(dtoWithFutureDate)
//
//        assertFalse(violationsFutureDate.isEmpty())
//
//        assertTrue(violationsFutureDate.any { it.messageTemplate.contains("{generic.validation.createdAt.pastOrPresent}") })
//    }
//
//    @Test
//    fun `should pass when all conditions are satisfied`() {
//
//        val dtoWithFutureDate = BankAccountDTO(
//            bankName = "Banco Teste",
//            accountNumber = "123456",
//            accountDigit = "1",
//            agency = "1234",
//            createdAt = LocalDateTime.now()
//        )
//
//        val violationsFutureDate = validator.validate(dtoWithFutureDate)
//
//        assertTrue(violationsFutureDate.isEmpty())
//    }
}