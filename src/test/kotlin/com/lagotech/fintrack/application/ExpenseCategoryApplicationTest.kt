package com.lagotech.fintrack.application

import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
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
        val expenseCategory = ExpenseCategoryDTO(
            name = "Luz",
            description = "Conta de Luz",
            color = "#FFFFFF",
            createdAt = LocalDateTime.now()
        )

        val savedCategory = service.save(expenseCategory)

        assertNotNull(savedCategory.id)
        assertEquals("Luz", savedCategory.name)
    }

    //TODO SAVE EXCEPTION
    //TODO FIND BY ID
    //TODO FIND BY ID EXCEPTION
    //TODO FIND ALL
    //TODO FIND ALL EXCEPTION
    //TODO UPDATE
    //TODO UPDATE EXCEPTION
    //TODO DELETE EXCEPTION
}