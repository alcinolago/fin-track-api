package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.domain.service.BankAccountService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bank")
class BankAccountController(private val service: BankAccountService) {

    @PostMapping
    private fun createBank(@Valid @RequestBody bank: BankAccountDTO): ResponseEntity<BankAccountDTO> {
        val createdBank = service.save(bank)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBank)
    }
}