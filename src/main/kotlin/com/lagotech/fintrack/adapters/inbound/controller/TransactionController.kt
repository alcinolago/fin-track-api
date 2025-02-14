package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.domain.service.TransactionService
import jakarta.validation.Valid
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transaction")
class TransactionController(private val service: TransactionService) {

    @PostMapping
    fun createTransaction(@Valid @RequestBody transactionDTO: TransactionDTO): ResponseEntity<TransactionDTO> {
        val createdTransaction = service.save(transactionDTO)
        val response =
            createdTransaction.add(linkTo(TransactionController::class.java).slash(transactionDTO.id).withSelfRel())
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<TransactionDTO>> {

        val transaction = service.findAll()

        transaction.forEach { transactionDTO ->
            transactionDTO.add(linkTo(TransactionController::class.java).slash(transactionDTO.id).withSelfRel())
        }
        return ResponseEntity.status(HttpStatus.OK).body(transaction)
    }

    @GetMapping("/{transactionId}")
    fun findById(@PathVariable("transactionId") transactionId: Long): ResponseEntity<TransactionDTO> {

        val transaction = service.findById(transactionId)

        val response = transaction.add(linkTo(TransactionController::class.java).slash(transaction.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @PutMapping("/{transactionId}")
    fun updatedTransaction(
        @PathVariable("transactionId") transactionId: Long,
        @Valid @RequestBody transactionDTO: TransactionDTO
    ): ResponseEntity<TransactionDTO> {

        if (transactionId <= 0) {
            throw IllegalArgumentException("ID must be provided and greater than zero")
        }

        val updatedTransaction = service.update(transactionId, transactionDTO)

        updatedTransaction.add(linkTo(methodOn(TransactionController::class.java).findById(transactionId)).withSelfRel())

        return ResponseEntity.ok(updatedTransaction)
    }

    @DeleteMapping("/{transactionId}")
    fun deleteTransaction(@PathVariable("transactionId") transactionId: Long): ResponseEntity<Unit> {
        if (transactionId <= 0) {
            throw IllegalArgumentException("ID must be greater than zero")
        }

        service.delete(transactionId)

        return ResponseEntity.noContent().build()
    }
}