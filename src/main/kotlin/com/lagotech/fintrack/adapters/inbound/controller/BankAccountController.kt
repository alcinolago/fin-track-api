package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.domain.service.BankAccountService
import jakarta.validation.Valid
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bank")
class BankAccountController(private val service: BankAccountService) {

    @PostMapping
    fun createBank(@Valid @RequestBody bank: BankAccountDTO): ResponseEntity<BankAccountDTO> {
        val createdBank =
            service.save(bank).add(linkTo(BankAccountController::class.java).slash(bank.id).withSelfRel())
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBank)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<BankAccountDTO>> {

        val bank = service.findAll()

        bank.forEach { bankDTO ->
            bankDTO.add(linkTo(BankAccountController::class.java).slash(bankDTO.id).withSelfRel())
        }
        return ResponseEntity.status(HttpStatus.OK).body(bank)
    }

    @GetMapping("/{bankId}")
    fun findById(@PathVariable("bankId") bankId: Long): ResponseEntity<BankAccountDTO> {

        val bank = service.findById(bankId)

        val response = bank.add(linkTo(BankAccountController::class.java).slash(bank.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @PutMapping("/{bankId}")
    fun updatebank(
        @PathVariable("bankId") bankId: Long,
        @Valid @RequestBody bankDTO: BankAccountDTO
    ): ResponseEntity<BankAccountDTO> {

        if (bankId <= 0) {
            throw IllegalArgumentException("ID must be provided and greater than zero")
        }

        val updatedbank = service.update(bankId, bankDTO)

        updatedbank.add(linkTo(methodOn(BankAccountController::class.java).findById(bankId)).withSelfRel())

        return ResponseEntity.ok(updatedbank)
    }

    @DeleteMapping("/{bankId}")
    fun deletebank(@PathVariable("bankId") bankId: Long): ResponseEntity<Unit> {
        if (bankId <= 0) {
            throw IllegalArgumentException("ID must be greater than zero")
        }

        service.delete(bankId)

        return ResponseEntity.noContent().build()
    }
}