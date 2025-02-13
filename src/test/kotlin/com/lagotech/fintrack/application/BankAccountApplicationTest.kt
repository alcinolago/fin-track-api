package com.lagotech.fintrack.application

import com.lagotech.fintrack.domain.model.BankAccount
import com.lagotech.fintrack.domain.service.BankAccountService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankAccountApplicationTest {

    @Autowired
    lateinit var service: BankAccountService

    @Test
    fun `should save bank account successfully`() {
        val bankAccount = BankAccount(
            bankName = "Bradesco",
            accountNumber = "654321",
            accountDigit = "2",
            agency = "0002",
            createdAt = LocalDateTime.now()
        )

        val savedAccount = service.save(bankAccount)

        assertNotNull(savedAccount.id)
        assertEquals("Bradesco", savedAccount.bankName)
    }
}