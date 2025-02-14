package com.lagotech.fintrack.application

import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.domain.model.TransactionType
import com.lagotech.fintrack.domain.service.TransactionService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionApplicationTest {

    @Autowired
    lateinit var transactionService: TransactionService

    @Test
    fun `should save transaction successfully`() {

        val transaction = TransactionDTO(
            id = null,
            transactionType = TransactionType.EXPENSE,
            categoryId = 1,
            bankId = 1,
            amount = BigDecimal(350.75),
            transactionDate = LocalDateTime.now(),
            notified = false,
            createdAt = LocalDateTime.now()
        )

        val savedTransaction = transactionService.save(transaction)

        assertNotNull(savedTransaction.id)
        assertEquals(TransactionType.EXPENSE, savedTransaction.transactionType)
        assertEquals(BigDecimal(350.75), savedTransaction.amount)
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