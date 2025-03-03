package com.lagotech.fintrack.validation

import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.dto.TransactionCategoryDTO
import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.domain.type.TransactionType
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class TransactionDTOValidationTest {

    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun `should fail when amount is negative value`() {
        val dto = TransactionDTO(
            id = 0,
            transactionType = TransactionType.EXPENSE,
            category = TransactionCategoryDTO(),
            bankAccount = BankAccountDTO(),
            amount = BigDecimal(-150),
            transactionDate = LocalDateTime.now(),
            notified = false,
            createdAt = LocalDateTime.now()
        )

        val violations = validator.validate(dto)

        assertFalse(violations.isEmpty())

        val amountNegativeViolation = violations.filter { it.messageTemplate.contains("{generic.validation.positive}") }

        assertEquals(1, amountNegativeViolation.size)
    }

    @Test
    fun `should fail when transaction and created data is in the future`() {
        val dto = TransactionDTO(
            id = 0,
            transactionType = TransactionType.EXPENSE,
            category = TransactionCategoryDTO(),
            bankAccount = BankAccountDTO(),
            amount = BigDecimal.ZERO,
            transactionDate = LocalDateTime.now().plusDays(1),
            notified = false,
            createdAt = LocalDateTime.now().plusDays(1)
        )

        val violations = validator.validate(dto)

        assertFalse(violations.isEmpty())

        val amountNegativeViolation =
            violations.filter { it.messageTemplate.contains("{generic.validation.createdAt.pastOrPresent}") }

        assertEquals(2, amountNegativeViolation.size)
    }

    @Test
    fun `should pass when all conditions are satisfied`() {
        val dto = TransactionDTO(
            transactionType = TransactionType.EXPENSE,
            category = TransactionCategoryDTO(),
            bankAccount = BankAccountDTO(),
            amount = BigDecimal(1),
            transactionDate = LocalDateTime.now(),
            notified = false,
            createdAt = LocalDateTime.now()
        )

        val violations = validator.validate(dto)

        assertTrue(violations.isEmpty())
    }
}