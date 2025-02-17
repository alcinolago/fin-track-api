package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.inbound.controller.BankAccountController
import com.lagotech.fintrack.adapters.inbound.controller.ExpenseCategoryController
import com.lagotech.fintrack.adapters.outbound.repository.TransactionRepository
import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.Transaction
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val repository: TransactionRepository,
    private val entityToDTOMapper: EntityToDTOMapper,
    private val messageSource: MessageSource,
    private val assembler: PagedResourcesAssembler<TransactionDTO>
) {

    fun save(transactionDTO: TransactionDTO): TransactionDTO {

        val entity = entityToDTOMapper.parseObject(transactionDTO, Transaction::class.java)

        val savedTransaction = repository.save(entity)

        return transactionToDTO(savedTransaction)
    }

    fun findById(id: Long): TransactionDTO {

        val transaction = repository.findById(id)
            .orElseThrow {
                ResourceNotFoundException(
                    messageSource.getMessage(
                        "{generic.validation.entity.id.notFound}",
                        arrayOf(id),
                        LocaleContextHolder.getLocale()
                    )
                )
            }

        return transactionToDTO(transaction)
    }

    fun findAll(pageable: Pageable): PagedModel<EntityModel<TransactionDTO>> {

        val transactionsPage = repository.findAllWithDetails(pageable)

        if (transactionsPage.isEmpty) {
            throw ResourceNotFoundException(
                messageSource.getMessage(
                    "{generic.validation.resource.notFound}",
                    null,
                    LocaleContextHolder.getLocale()
                )
            )
        }

        val transactions = transactionsPage.map { transaction -> transactionToPagedDTO(transaction) }

        return assembler.toModel(transactions)
    }

//    fun update(id: Long, transactionDTO: TransactionDTO): TransactionDTO {
//
//        val existingTransaction = repository.findById(id)
//            .orElseThrow { ResourceNotFoundException(messageSource.getMessage("{generic.validation.entity.id.notFound}", arrayOf(id), LocaleContextHolder.getLocale())) }
//
//        transactionDTO.transactionType?.let {
//            existingTransaction.transactionType = it
//        }
//        transactionDTO.categoryId.let {
//            existingTransaction.categoryId = it
//        }
//        transactionDTO.bankId.let {
//            existingTransaction.bankId = it
//        }
//        transactionDTO.amount?.let {
//            existingTransaction.amount = it
//        }
//        transactionDTO.transactionDate?.let {
//            existingTransaction.transactionDate = it
//        }
//        existingTransaction.notified = transactionDTO.notified
//
//        val savedTransaction = repository.save(existingTransaction)
//
//        return entityToDTOMapper.parseObject(savedTransaction, TransactionDTO::class.java)
//    }

    fun delete(id: Long) {
        val transaction = repository.findById(id)
            .orElseThrow {
                ResourceNotFoundException(
                    messageSource.getMessage(
                        "{generic.validation.entity.id.notFound}",
                        arrayOf(id),
                        LocaleContextHolder.getLocale()
                    )
                )
            }

        repository.delete(transaction)
    }

    fun transactionToDTO(transaction: Transaction): TransactionDTO {
        return TransactionDTO(
            id = transaction.id,
            transactionType = transaction.transactionType,
            category = ExpenseCategoryDTO(
                id = transaction.category.id,
                name = transaction.category.name,
                color = transaction.category.color
            ).apply {
                add(linkTo(methodOn(ExpenseCategoryController::class.java).findById(id)).withSelfRel()) // Link da categoria
            },
            bankAccount = BankAccountDTO(
                id = transaction.bankAccount.id,
                bankName = transaction.bankAccount.bankName,
                accountNumber = transaction.bankAccount.accountNumber,
                accountDigit = transaction.bankAccount.accountDigit,
                agency = transaction.bankAccount.agency
            ).apply {
                add(linkTo(methodOn(BankAccountController::class.java).findById(id)).withSelfRel()) // Link da conta bancária
            },
            amount = transaction.amount,
            transactionDate = transaction.transactionDate,
            notified = transaction.notified,
            createdAt = transaction.createdAt
        )
    }

    fun transactionToPagedDTO(transaction: Transaction): TransactionDTO {
        return TransactionDTO(
            id = transaction.id,
            transactionType = transaction.transactionType,
            category = ExpenseCategoryDTO(
                id = transaction.category.id,
                name = transaction.category.name,
                color = transaction.category.color
            ).apply {
                add(linkTo(methodOn(ExpenseCategoryController::class.java).findById(id)).withSelfRel()) // Link da categoria
            },
            bankAccount = BankAccountDTO(
                id = transaction.bankAccount.id,
                bankName = transaction.bankAccount.bankName,
                accountNumber = transaction.bankAccount.accountNumber,
                accountDigit = transaction.bankAccount.accountDigit,
                agency = transaction.bankAccount.agency
            ).apply {
                add(linkTo(methodOn(BankAccountController::class.java).findById(id)).withSelfRel()) // Link da conta bancária
            },
            amount = transaction.amount,
            transactionDate = transaction.transactionDate,
            notified = transaction.notified,
            createdAt = transaction.createdAt
        )
    }
}