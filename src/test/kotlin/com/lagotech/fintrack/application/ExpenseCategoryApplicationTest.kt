package com.lagotech.fintrack.application

import com.lagotech.fintrack.adapters.outbound.repository.ExpenseCategoryRepository
import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
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
    private lateinit var service: ExpenseCategoryService

    @Autowired
    private lateinit var repository: ExpenseCategoryRepository

//    @Test
//    fun `should save expense category successfully`() {
//        val expenseCategory = ExpenseCategory(
//            name = "Luz",
//            description = "Conta de Luz",
//            color = "#FFFFFF",
//            createdAt = LocalDateTime.now()
//        )
//
//        val savedCategory = repository.save(expenseCategory)
//
//        val savedCategory = service.save(expenseCategory)
//
//        assertNotNull(savedCategory.id)
//        assertEquals("Luz", savedCategory.name)
//    }

    //TODO SAVE EXCEPTION
    //TODO FIND BY ID
    //TODO FIND BY ID EXCEPTION
    //TODO FIND ALL
    //TODO FIND ALL EXCEPTION
    //TODO UPDATE
    //TODO UPDATE EXCEPTION
    //TODO DELETE EXCEPTION
}