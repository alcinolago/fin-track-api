package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.extension.BankAccountMapper
import com.lagotech.fintrack.application.request.BankAccountRequest
import com.lagotech.fintrack.application.response.BankAccountResponse
import com.lagotech.fintrack.domain.service.BankAccountService
import com.lagotech.fintrack.domain.service.UserService
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
class BankAccountController(
    private val service: BankAccountService,
    private val userService: UserService
) {

    @Operation(summary = "Cadastrar uma nova conta bancária")
    @PostMapping
    fun save(@Valid @RequestBody req: BankAccountRequest): ResponseEntity<BankAccountResponse> {

        val user = userService.findById(req.userId)
            .orElseThrow { ResourceNotFoundException("") }

        val savedBankAccount = service.save(BankAccountMapper.toEntity(req, user))

        return ResponseEntity.status(HttpStatus.CREATED).body(BankAccountMapper.toResponse(savedBankAccount))
    }

    @Operation(summary = "Listar conta bancária")
    @GetMapping("/accounts/{userId}/account")
    fun findByUserId(@PathVariable("userId") userId: Long): ResponseEntity<List<BankAccountResponse>> {

        if (userId <= 0) {
            throw ResourceNotFoundException("Id invalido")
        }

        val bankAccounts = service.findByUserId(userId)

        if (bankAccounts.isEmpty()) {
            return ResponseEntity.noContent().build()
        }

        return ResponseEntity.ok().body(BankAccountMapper.toResponseList(bankAccounts))
    }

    @Operation(summary = "Buscar conta bancária por ID")
    @GetMapping("/{bankId}")
    fun findById(@PathVariable("bankId") bankId: Long): ResponseEntity<BankAccountResponse> {

        val bank = service.findById(bankId)
            .orElseThrow { ResourceNotFoundException("BankAccount with id $bankId not found") }

        val response = BankAccountMapper.toResponse(bank)
            .add(linkTo(BankAccountController::class.java).slash(bank.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @Operation(summary = "Atualizar Conta Bancária")
    @PutMapping("/{bankId}")
    fun update(
        @PathVariable("bankId") bankId: Long,
        @Valid @RequestBody req: BankAccountRequest
    ): ResponseEntity<BankAccountResponse> {

        if (bankId <= 0) {
            throw IllegalArgumentException("ID must be provided and greater than zero")
        }

        val bankAccount = service.findById(bankId)
            .orElseThrow { ResourceNotFoundException("Id inexistente") }

        bankAccount.bankName = req.bankName
        bankAccount.accountNumber = req.accountNumber
        bankAccount.accountDigit = req.accountDigit
        bankAccount.agency = req.agency
        bankAccount.balance = req.balance

        val updatedbank = service.update(bankAccount)

        updatedbank.add(linkTo(methodOn(BankAccountController::class.java).findById(bankId)).withSelfRel())

        return ResponseEntity.ok(updatedbank)
    }

    @Operation(summary = "Apagar Conta Bancária")
    @DeleteMapping("/{bankId}")
    fun deletebank(@PathVariable("bankId") bankId: Long): ResponseEntity<Unit> {

        if (bankId <= 0) {
            throw IllegalArgumentException("ID must be greater than zero")
        }

        val bankAccount = service.findById(bankId)
            .orElseThrow { ResourceNotFoundException("Id inexistente") }

        service.delete(bankAccount)

        return ResponseEntity.noContent().build()
    }
}