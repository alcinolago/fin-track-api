package com.lagotech.fintrack.application

import com.lagotech.fintrack.domain.model.ExpenseCategory
import com.lagotech.fintrack.domain.service.ExpenseCategoryService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExpenseCategoryApplicationTest {

    @Autowired
    lateinit var service: ExpenseCategoryService

    @Test
    fun `should save expense category successfully`() {
        val expenseCategory = ExpenseCategory(
            name = "Luz",
            description = "Conta de Luz",
            color = "#FFFFFF",
            createdAt = LocalDateTime.now()
        )

        val savedCategory = service.save(expenseCategory)

        assertNotNull(savedCategory.id)
        assertEquals("Luz", savedCategory.name)
    }
}