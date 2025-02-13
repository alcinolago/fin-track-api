package com.lagotech.fintrack.validation

import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class ExpenseCategoryDTOValidationTest {

    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun `should fail when required fields are blank`(){
        val dto = ExpenseCategoryDTO(
            name = "",
            description = "",
            color = "",
            createdAt = LocalDateTime.now()
        )

        val violations = validator.validate(dto)

        assertFalse(violations.isEmpty())

        val blankFieldsViolation = violations.filter { it.message.contains("Não pode estar em branco") }

        assertEquals(2, blankFieldsViolation.size)
    }

    @Test
    fun `should fail when name has less than 3 or more than 50 characters`(){
        val dto = ExpenseCategoryDTO(
            name = "a",
            description = "",
            color = "#FFFFFF",
            createdAt = LocalDateTime.now()
        )

        val violations = validator.validate(dto)

        assertFalse(violations.isEmpty())

        val blankFieldsViolation = violations.filter { it.message.contains("O nome deve ter entre 3 e 50 caracteres") }

        assertEquals(1, blankFieldsViolation.size)
    }

    @Test
    fun `should fail when color is not hexadecimal format #FFFFFF`(){
        val dto = ExpenseCategoryDTO(
            name = "a",
            description = "",
            color = "red",
            createdAt = LocalDateTime.now()
        )

        val violations = validator.validate(dto)

        assertFalse(violations.isEmpty())

        val blankFieldsViolation = violations.filter { it.message.contains("A cor deve estar no formato hexadecimal (ex: #FFFFFF)") }

        assertEquals(1, blankFieldsViolation.size)
    }

    @Test
    fun `should fail when created at is in the future`(){
        val dto = ExpenseCategoryDTO(
            name = "Luz",
            description = "Conta de Luz",
            color = "#FFFFFF",
            createdAt = LocalDateTime.now().plusDays(1)
        )

        val violations = validator.validate(dto)

        assertFalse(violations.isEmpty())

        val blankFieldsViolation = violations.filter { it.message.contains("A data de criação não pode estar no futuro") }

        assertEquals(1, blankFieldsViolation.size)
    }

    @Test
    fun `should pass when all conditions are satisfied`(){
        val dto = ExpenseCategoryDTO(
            name = "Luz",
            description = "Conta de Luz",
            color = "#FFFFFF",
            createdAt = LocalDateTime.now()
        )

        val violations = validator.validate(dto)

        assertTrue(violations.isEmpty())
    }
}