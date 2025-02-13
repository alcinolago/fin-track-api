package com.lagotech.fintrack.application

import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.domain.model.TransactionType
import com.lagotech.fintrack.domain.service.BankAccountService
import com.lagotech.fintrack.domain.service.ExpenseCategoryService
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
    lateinit var bankAccountService: BankAccountService

    @Autowired
    lateinit var expenseCategoryService: ExpenseCategoryService

    @Autowired
    lateinit var transactionService: TransactionService

    @Test
    fun `should save transaction successfully`() {

        val bankAccount = BankAccountDTO(
            bankName = "Bradesco",
            accountNumber = "654321",
            accountDigit = "2",
            agency = "0002",
            createdAt = LocalDateTime.now()
        )

        bankAccountService.save(bankAccount)

        val expenseCategory = ExpenseCategoryDTO(
            name = "Luz",
            description = "Conta de Luz",
            color = "#FFFFFF",
            createdAt = LocalDateTime.now()
        )

        expenseCategoryService.save(expenseCategory)

        expenseCategory.id = 1
        bankAccount.id = 1

        val transaction = TransactionDTO(
            id = null,
            transactionType = TransactionType.EXPENSE,
            category = expenseCategory,
            bank = bankAccount,
            amount = BigDecimal(350.75),
            transactionDate = LocalDateTime.now(),
            notified = false,
            createdAt = LocalDateTime.now()
        )

        val savedTransaction = transactionService.save(transaction)

        assertNotNull(savedTransaction.id)
        assertEquals(TransactionType.EXPENSE, savedTransaction.transactionType)
        assertEquals("Luz", savedTransaction.category?.name)
        assertEquals(BigDecimal(350.75), savedTransaction.amount)
    }
}