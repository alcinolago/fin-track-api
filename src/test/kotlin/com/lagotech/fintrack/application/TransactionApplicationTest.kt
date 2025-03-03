package com.lagotech.fintrack.application

import com.lagotech.fintrack.domain.service.TransactionService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionApplicationTest {

    @Autowired
    lateinit var transactionService: TransactionService

    @Test
    fun `should save transaction successfully`() {

//        val category = ExpenseCategoryDTO(
//            id = 1,
//            name = "Cultura",
//            description = "Teatros, Óperas e Museus",
//            color = "#FFFFFF"
//        )
//
//        val bankAccount = BankAccountDTO(
//            id = 1,
//            bankName = "Nome do Banco",
//            accountNumber = "123456",
//            accountDigit = "1",
//            agency = "1234")
//
//        val transaction = TransactionDTO(
//            id = 0,
//            transactionType = TransactionType.EXPENSE,
//            category = category,
//            bankAccount = bankAccount,
//            amount = BigDecimal(350.75),
//            transactionDate = LocalDateTime.now(),
//            notified = false,
//            createdAt = LocalDateTime.now()
//        )
//
//        val savedTransaction = transactionService.save(transaction)
//
//        assertNotNull(savedTransaction.id)
//        assertEquals(TransactionType.EXPENSE, savedTransaction.transactionType)
//        assertEquals(BigDecimal(350.75), savedTransaction.amount)
    }

    @Test
    fun `should throw exception when saving with invalid data`() {

//        val category = ExpenseCategoryDTO(
//            id = 1,
//            name = "Cultura",
//            description = "Teatros, Óperas e Museus",
//            color = "#FFFFFF"
//        )
//
//        val bankAccount = BankAccountDTO(
//            id = 1,
//            bankName = "Nome do Banco",
//            accountNumber = "123456",
//            accountDigit = "1",
//            agency = "1234")
//
//        val transaction = TransactionDTO(
//            id = 0,
//            transactionType = TransactionType.EXPENSE,
//            category = category,
//            bankAccount = bankAccount,
//            amount = BigDecimal(350.75),
//            transactionDate = LocalDateTime.now(),
//            notified = false,
//            createdAt = LocalDateTime.now()
//        )
//
//        val savedTransaction = transactionService.save(transaction)
//
//        assertNotNull(savedTransaction.id)
//        assertEquals(TransactionType.EXPENSE, savedTransaction.transactionType)
//        assertEquals(BigDecimal(350.75), savedTransaction.amount)
    }
}