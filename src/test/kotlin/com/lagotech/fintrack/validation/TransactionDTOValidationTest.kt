package com.lagotech.fintrack.validation

import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.domain.model.BankAccount
import com.lagotech.fintrack.domain.model.ExpenseCategory
import com.lagotech.fintrack.domain.model.TransactionType
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
    fun `should fail when required fields are null`(){
        val dto = TransactionDTO(
            transactionType = null,
            category = null,
            bank = null,
            amount = null,
            transactionDate = LocalDateTime.now(),
            notified = false,
            createdAt = LocalDateTime.now()
        )

        val violations = validator.validate(dto)

        assertFalse(violations.isEmpty())

        val nullViolations = violations.filter { it.message.contains("Não pode ser nulo") }

        assertEquals(4, nullViolations.size)
    }

    @Test
    fun `should fail when amount is negative value`(){
        val dto = TransactionDTO(
            transactionType = TransactionType.EXPENSE,
            category = ExpenseCategory(),
            bank = BankAccount(),
            amount = BigDecimal(-150.25),
            transactionDate = LocalDateTime.now(),
            notified = false,
            createdAt = LocalDateTime.now()
        )

        val violations = validator.validate(dto)

        assertFalse(violations.isEmpty())

        val amountNegativeViolation = violations.filter { it.message.contains("O valor da transação deve ser maior que zero") }

        assertEquals(1, amountNegativeViolation.size)
    }

    @Test
    fun `should fail when transaction and created data is in the future`(){
        val dto = TransactionDTO(
            transactionType = TransactionType.EXPENSE,
            category = ExpenseCategory(),
            bank = BankAccount(),
            amount = BigDecimal(150.25),
            transactionDate = LocalDateTime.now().plusDays(1),
            notified = false,
            createdAt = LocalDateTime.now().plusDays(1)
        )

        val violations = validator.validate(dto)

        assertFalse(violations.isEmpty())

        val amountNegativeViolation = violations.filter { it.message.contains("A data não pode estar no futuro") }

        assertEquals(2, amountNegativeViolation.size)
    }

    @Test
    fun `should pass when all conditions are satisfied`(){
        val dto = TransactionDTO(
            transactionType = TransactionType.EXPENSE,
            category = ExpenseCategory(),
            bank = BankAccount(),
            amount = BigDecimal(150.25),
            transactionDate = LocalDateTime.now(),
            notified = false,
            createdAt = LocalDateTime.now()
        )

        val violations = validator.validate(dto)

        assertTrue(violations.isEmpty())
    }
}