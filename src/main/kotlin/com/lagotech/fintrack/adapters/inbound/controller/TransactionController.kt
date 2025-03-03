package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.extension.TransactionMapper
import com.lagotech.fintrack.application.request.TransactionRequest
import com.lagotech.fintrack.application.response.TransactionResponse
import com.lagotech.fintrack.domain.service.BankAccountService
import com.lagotech.fintrack.domain.service.TransactionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Transações", description = "Gerencia as transações financeiras")
@RestController
@RequestMapping("/transaction")
class TransactionController(
    private val service: TransactionService,
    private val bankAccountService: BankAccountService
) {

    @Operation(summary = "Criar uma nova transação")
    @PostMapping
    fun save(@Valid @RequestBody request: TransactionRequest): ResponseEntity<TransactionResponse> {

        val bankAccount = bankAccountService.findById(request.bankAccountId)
            .orElseThrow { ResourceNotFoundException("A Account Bank with id ${request.bankAccountId} not found") }

        val response = service.save(TransactionMapper.toEntity(bankAccount, request))

        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionMapper.toResponse(response))
    }

    @Operation(summary = "Listar transações por usuário")
    @GetMapping("/user/{userId}")
    fun findByUserId(
        @PathVariable userId: Long,
        pageable: Pageable
    ): ResponseEntity<PagedModel<EntityModel<TransactionResponse>>> {
        return ResponseEntity.ok(service.findByUserId(userId, pageable))
    }

    @Operation(summary = "Buscar transação por ID")
    @GetMapping("/{transactionId}")
    fun findById(
        @PathVariable("transactionId") transactionId: Long
    ): ResponseEntity<TransactionResponse> {

        if (transactionId <= 0) {
            throw ResourceNotFoundException("Id invalido")
        }

        val transaction = service.findById(transactionId)
            .orElseThrow { ResourceNotFoundException("Id não encontrado") }

        return ResponseEntity.status(HttpStatus.OK).body(TransactionMapper.toResponse(transaction))
    }

    @Operation(summary = "Atualizar transação")
    @PutMapping("/{transactionId}")
    fun update(
        @PathVariable("transactionId") transactionId: Long,
        @Valid @RequestBody request: TransactionRequest
    ): ResponseEntity<TransactionResponse> {

        if (transactionId <= 0) {
            throw IllegalArgumentException("ID must be provided and greater than zero")
        }

        val transaction = service.findById(transactionId)
            .orElseThrow { ResourceNotFoundException("Id inexistente") }

        val bankAccount = bankAccountService.findById(request.bankAccountId)
            .orElseThrow { ResourceNotFoundException("A Account Bank with id ${request.bankAccountId} not found") }

        transaction.status = request.status
        transaction.type = request.type
        transaction.bankAccount = bankAccount
        transaction.amount = request.amount
        transaction.dueDate = request.dueDate

        val response = service.update(transaction)

        response.add(linkTo(methodOn(TransactionController::class.java).findById(transactionId)).withSelfRel())

        return ResponseEntity.ok(response)
    }

    @Operation(summary = "Remover transação")
    @DeleteMapping("/{transactionId}")
    fun delete(@PathVariable("transactionId") transactionId: Long): ResponseEntity<Unit> {

        if (transactionId <= 0) {
            throw IllegalArgumentException("ID must be greater than zero")
        }

        val transaction = service.findById(transactionId)
            .orElseThrow { ResourceNotFoundException("Id inexistente") }

        service.delete(transaction)

        return ResponseEntity.noContent().build()
    }
}