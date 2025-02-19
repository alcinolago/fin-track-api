package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.domain.service.BankAccountService
import com.lagotech.fintrack.domain.service.ExpenseCategoryService
import com.lagotech.fintrack.domain.service.TransactionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Transações", description = "Gerencia as transações financeiras")
@RestController
@RequestMapping("/transaction")
class TransactionController(
    private val service: TransactionService,
    private val categoryService: ExpenseCategoryService,
    private val accountService: BankAccountService
) {

    @Operation(
        summary = "Criar uma nova transação",
        description = "Salva uma nova transação no sistema."
    )
    @PostMapping
    fun createTransaction(@Valid @RequestBody transactionDTO: TransactionDTO): ResponseEntity<TransactionDTO> {

        categoryService.findById(transactionDTO.category.id)
            .orElseThrow { ResourceNotFoundException("A Category with id ${transactionDTO.category.id} not found") }

        accountService.findById(transactionDTO.bankAccount.id)
            .orElseThrow { ResourceNotFoundException("A Account Bank with id ${transactionDTO.bankAccount.id} not found") }

        val createdTransaction = service.save(transactionDTO)

        val response = createdTransaction.add(
            linkTo(TransactionController::class.java).slash(createdTransaction.id).withSelfRel()
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @Operation(
        summary = "Listar transações",
        description = "Listar todas as transações."
    )
    @GetMapping
    fun findAll(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "limit", defaultValue = "2") limit: Int,
        @RequestParam(value = "sort", defaultValue = "asc") sort: String,
    ): ResponseEntity<PagedModel<EntityModel<TransactionDTO>>> {

        val sortDirection: Sort.Direction =
            if("desc".equals(sort, ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC
        val pageable: Pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "transactionType"));
        val transaction = service.findAll(pageable)

        transaction.forEach { transactionDTO ->
            transactionDTO.add(linkTo(TransactionController::class.java).slash(transactionDTO.content?.id).withSelfRel())
        }
        return ResponseEntity.status(HttpStatus.OK).body(transaction)
    }

    @Operation(
        summary = "Buscar transação por ID",
        description = "Retorna uma transação específica pelo seu ID",
    )
    @GetMapping("/{transactionId}")
    fun findById(
        @Parameter(description = "ID da transação a ser buscada", example = "1")
        @PathVariable("transactionId") transactionId: Long
    ): ResponseEntity<TransactionDTO> {

        val transaction = service.findById(transactionId)

        val response = transaction.add(linkTo(TransactionController::class.java).slash(transaction.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

//    @Operation(
//        summary = "Atualizar transação",
//        description = "Atualizar transação pelo id informado."
//    )
//    @PutMapping("/{transactionId}")
//    fun updatedTransaction(
//        @PathVariable("transactionId") transactionId: Long,
//        @Valid @RequestBody transactionDTO: TransactionDTO
//    ): ResponseEntity<TransactionDTO> {
//
//        if (transactionId <= 0) {
//            throw IllegalArgumentException("ID must be provided and greater than zero")
//        }
//
//        val updatedTransaction = service.update(transactionId, transactionDTO)
//
//        updatedTransaction.add(linkTo(methodOn(TransactionController::class.java).findById(transactionId)).withSelfRel())
//
//        return ResponseEntity.ok(updatedTransaction)
//    }

    @Operation(
        summary = "Remover transação",
        description = "Remover transação pelo id informado."
    )
    @DeleteMapping("/{transactionId}")
    fun deleteTransaction(@PathVariable("transactionId") transactionId: Long): ResponseEntity<Unit> {
        if (transactionId <= 0) {
            throw IllegalArgumentException("ID must be greater than zero")
        }

        service.delete(transactionId)

        return ResponseEntity.noContent().build()
    }
}