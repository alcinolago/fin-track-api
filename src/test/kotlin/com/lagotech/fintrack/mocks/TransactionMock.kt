package com.lagotech.fintrack.mocks

import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.Transaction
import com.lagotech.fintrack.domain.model.TransactionType
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime

@Component
class TransactionMock(
    private val entityToDTOMapper: EntityToDTOMapper,
    private val expenseCategoryMock: ExpenseCategoryMock,
    private val bankAccountMock: BankAccountMock
) {

    fun getTransaction(): Transaction {
        return Transaction(
            id = 1,
            transactionType = TransactionType.EXPENSE,
            category = expenseCategoryMock.mockExpenseCategory(),
            bank = bankAccountMock.mockBankAccount(),
            amount = BigDecimal(1000),
            transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
            notified = false,
            createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
        )
    }

    fun getTransactionDTO(): TransactionDTO {
        return TransactionDTO(
            id = 1,
            transactionType = TransactionType.EXPENSE,
            category = expenseCategoryMock.mockExpenseCategoryDTO(),
            bank = bankAccountMock.mockBankAccountDTO(),
            amount = BigDecimal(1000),
            transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
            notified = false,
            createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
        )
    }

    fun getTransactionList(): List<Transaction> {
        return listOf(
            Transaction(
                id = 1,
                transactionType = TransactionType.EXPENSE,
                category = expenseCategoryMock.mockExpenseCategory(),
                bank = bankAccountMock.mockBankAccount(),
                amount = BigDecimal(1000),
                transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
                notified = false,
                createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
            ),
            Transaction(
                id = 2,
                transactionType = TransactionType.EXPENSE,
                category = expenseCategoryMock.mockExpenseCategory(),
                bank = bankAccountMock.mockBankAccount(),
                amount = BigDecimal(2000),
                transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
                notified = false,
                createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
            )
        )
    }

    fun getTransactionDTOList(): List<TransactionDTO> {
        return listOf(
            TransactionDTO(
                id = 1,
                transactionType = TransactionType.EXPENSE,
                category = expenseCategoryMock.mockExpenseCategoryDTO(),
                bank = bankAccountMock.mockBankAccountDTO(),
                amount = BigDecimal(1000),
                transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
                notified = false,
                createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
            ),
            TransactionDTO(
                id = 2,
                transactionType = TransactionType.EXPENSE,
                category = expenseCategoryMock.mockExpenseCategoryDTO(),
                bank = bankAccountMock.mockBankAccountDTO(),
                amount = BigDecimal(2000),
                transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
                notified = false,
                createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
            )
        )
    }

    fun mockEntityToDTO(): TransactionDTO {
        return entityToDTOMapper.parseObject(getTransaction(), TransactionDTO::class.java)
    }

    fun mockDTOToEntity(): Transaction {
        return entityToDTOMapper.parseObject(getTransactionDTO(), Transaction::class.java)
    }

    fun mockEntityListToDTOList(): List<TransactionDTO> {
        return entityToDTOMapper.parseListObjects(getTransactionList(), TransactionDTO::class.java)
    }

    fun mockDTOListToEntityList(): List<Transaction> {
        return entityToDTOMapper.parseListObjects(getTransactionDTOList(), Transaction::class.java)
    }
}