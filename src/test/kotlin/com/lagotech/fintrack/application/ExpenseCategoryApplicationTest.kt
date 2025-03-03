package com.lagotech.fintrack.application

import com.lagotech.fintrack.adapters.outbound.repository.TransactionCategoryRepository
import com.lagotech.fintrack.domain.service.TransactionCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExpenseCategoryApplicationTest {

    @Autowired
    private lateinit var service: TransactionCategoryService

    @Autowired
    private lateinit var repository: TransactionCategoryRepository

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