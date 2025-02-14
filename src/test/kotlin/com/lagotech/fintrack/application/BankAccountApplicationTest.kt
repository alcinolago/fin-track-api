package com.lagotech.fintrack.application

import com.lagotech.fintrack.application.dto.BankAccountDTO
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
        val bankAccount = BankAccountDTO(
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

    //TODO SAVE EXCEPTION
    //TODO FIND BY ID
    //TODO FIND BY ID EXCEPTION
    //TODO FIND ALL
    //TODO FIND ALL EXCEPTION
    //TODO UPDATE
    //TODO UPDATE EXCEPTION
    //TODO DELETE EXCEPTION

}