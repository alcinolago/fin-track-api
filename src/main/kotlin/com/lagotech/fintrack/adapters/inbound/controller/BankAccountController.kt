package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.domain.service.BankAccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Conta Bancária", description = "Gerencia as contas bancárias")
@RestController
@RequestMapping("/bank")
class BankAccountController(private val service: BankAccountService) {

    @Operation(
        summary = "Cadastrar uma nova conta bancária",
        description = "Salva uma nova conta bancária no sistema."
    )
    @PostMapping
    fun createBank(@Valid @RequestBody bank: BankAccountDTO): ResponseEntity<BankAccountDTO> {
        val createdBank =
            service.save(bank).add(linkTo(BankAccountController::class.java).slash(bank.id).withSelfRel())
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBank)
    }

    @Operation(
        summary = "Listar contas bancárias",
        description = "Listar todas as contas bancárias."
    )
    @GetMapping
    fun findAll(): ResponseEntity<List<BankAccountDTO>> {

        val bank = service.findAll()

        bank.forEach { bankDTO ->
            bankDTO.add(linkTo(BankAccountController::class.java).slash(bankDTO.id).withSelfRel())
        }
        return ResponseEntity.status(HttpStatus.OK).body(bank)
    }

    @Operation(
        summary = "Buscar conta bancária por ID",
        description = "Retorna uma conta bancária específica pelo seu ID",
    )
    @GetMapping("/{bankId}")
    fun findById(@PathVariable("bankId") bankId: Long): ResponseEntity<BankAccountDTO> {

        val bank = service.findById(bankId)
            .orElseThrow { ResourceNotFoundException("BankAccount with id $bankId not found") }

        val response = bank.add(linkTo(BankAccountController::class.java).slash(bank.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @Operation(
        summary = "Atualizar Conta Bancária",
        description = "Atualizar Conta Bancária pelo id informado."
    )
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

    @Operation(
        summary = "Remover Conta Bancária",
        description = "Remover Conta Bancária pelo id informado."
    )
    @DeleteMapping("/{bankId}")
    fun deletebank(@PathVariable("bankId") bankId: Long): ResponseEntity<Unit> {
        if (bankId <= 0) {
            throw IllegalArgumentException("ID must be greater than zero")
        }

        service.delete(bankId)

        return ResponseEntity.noContent().build()
    }
}