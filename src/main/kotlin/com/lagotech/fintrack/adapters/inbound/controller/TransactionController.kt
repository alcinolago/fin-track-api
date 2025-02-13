package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.dto.TransactionDTO
import com.lagotech.fintrack.domain.service.TransactionService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transaction")
class TransactionController(private val service: TransactionService) {

    @PostMapping
    fun createTransaction(@Valid @RequestBody transactionDTO: TransactionDTO): ResponseEntity<TransactionDTO> {
        val createdTransaction = service.save(transactionDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction)
    }
}