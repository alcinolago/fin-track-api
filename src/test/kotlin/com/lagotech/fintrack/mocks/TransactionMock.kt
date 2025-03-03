package com.lagotech.fintrack.mocks

import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import org.springframework.stereotype.Component

@Component
class TransactionMock(
    private val entityToDTOMapper: EntityToDTOMapper
) {

//    val categoryDTO = ExpenseCategoryDTO(
//        id = 1,
//        name = "Cultura",
//        description = "Teatros, Óperas e Museus",
//        color = "#FFFFFF"
//    )
//
//    val bankAccountDTO = BankAccountDTO(
//        id = 1,
//        bankName = "Nome do Banco",
//        accountNumber = "123456",
//        accountDigit = "1",
//        agency = "1234")
//
//    val category = ExpenseCategory(
//        id = 1,
//        name = "Cultura",
//        description = "Teatros, Óperas e Museus",
//        color = "#FFFFFF"
//    )
//
//    val bankAccount = BankAccount(
//        id = 1,
//        bankName = "Nome do Banco",
//        accountNumber = "123456",
//        accountDigit = "1",
//        agency = "1234")
//
//    fun getTransaction(): Transaction {
//        return Transaction(
//            id = 1,
//            transactionType = TransactionType.EXPENSE,
//            category = category,
//            bankAccount = bankAccount,
//            amount = BigDecimal(1000),
//            transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
//            notified = false,
//            createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
//        )
//    }
//
//    fun getTransactionDTO(): TransactionDTO {
//        return TransactionDTO(
//            id = 1,
//            transactionType = TransactionType.EXPENSE,
//            category = categoryDTO,
//            bankAccount = bankAccountDTO,
//            amount = BigDecimal(1000),
//            transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
//            notified = false,
//            createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
//        )
//    }
//
//    fun getTransactionList(): List<Transaction> {
//        return listOf(
//            Transaction(
//                id = 1,
//                transactionType = TransactionType.EXPENSE,
//                category = category,
//                bankAccount = bankAccount,
//                amount = BigDecimal(1000),
//                transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
//                notified = false,
//                createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
//            ),
//            Transaction(
//                id = 2,
//                transactionType = TransactionType.EXPENSE,
//                category = category,
//                bankAccount = bankAccount,
//                amount = BigDecimal(2000),
//                transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
//                notified = false,
//                createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
//            )
//        )
//    }
//
//    fun getTransactionDTOList(): List<TransactionDTO> {
//        return listOf(
//            TransactionDTO(
//                id = 1,
//                transactionType = TransactionType.EXPENSE,
//                category = categoryDTO,
//                bankAccount = bankAccountDTO,
//                amount = BigDecimal(1000),
//                transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
//                notified = false,
//                createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
//            ),
//            TransactionDTO(
//                id = 2,
//                transactionType = TransactionType.EXPENSE,
//                category = categoryDTO,
//                bankAccount = bankAccountDTO,
//                amount = BigDecimal(2000),
//                transactionDate = LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0),
//                notified = false,
//                createdAt = LocalDateTime.of(2025, 2, 11, 14, 30, 0, 0)
//            )
//        )
//    }
//
//    fun mockEntityToDTO(): TransactionDTO {
//        return entityToDTOMapper.parseObject(getTransaction(), TransactionDTO::class.java)
//    }
//
//    fun mockDTOToEntity(): Transaction {
//        return entityToDTOMapper.parseObject(getTransactionDTO(), Transaction::class.java)
//    }
//
//    fun mockEntityListToDTOList(): List<TransactionDTO> {
//        return entityToDTOMapper.parseListObjects(getTransactionList(), TransactionDTO::class.java)
//    }
//
//    fun mockDTOListToEntityList(): List<Transaction> {
//        return entityToDTOMapper.parseListObjects(getTransactionDTOList(), Transaction::class.java)
//    }
}