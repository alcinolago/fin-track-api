package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.extension.SavingsGoalMapper
import com.lagotech.fintrack.application.request.SavingsGoalRequest
import com.lagotech.fintrack.application.response.SavingsGoalResponse
import com.lagotech.fintrack.domain.service.SavingsGoalService
import com.lagotech.fintrack.domain.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Meta de Poupança", description = "Gerenciar metas de poupança")
@RestController
@RequestMapping("/saving")
class SavingsGoalController(
    private val service: SavingsGoalService,
    private val userService: UserService
) {

    @PostMapping
    fun save(@RequestBody req: SavingsGoalRequest): ResponseEntity<SavingsGoalResponse> {

        val user = userService.findById(req.userId)
            .orElseThrow { ResourceNotFoundException("Id inexistente ou não encontrado") }

        val response = service.createSavingGoal(SavingsGoalMapper.toEntity(req, user))

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{savingsId}")
    fun findById(@PathVariable("savingsId") savingsId: Long): ResponseEntity<SavingsGoalResponse> {

        if (savingsId <= 0) {
            throw ResourceNotFoundException("Id inexistente ou não encontrado")
        }

        val savingsGoal = service.findById(savingsId)
            .orElseThrow { ResourceNotFoundException("Não encontrado") }

        return ResponseEntity.status(HttpStatus.OK).body(SavingsGoalMapper.toResponse(savingsGoal))
    }

    @GetMapping("/users/{userId}/savings")
    fun findByUserId(@PathVariable("userId") userId: Long): ResponseEntity<List<SavingsGoalResponse>> {

        if (userId <= 0) {
            throw ResourceNotFoundException("Id inexistente ou não encontrado")
        }

        val savingsGoal = service.findByUserId(userId)

        return ResponseEntity.status(HttpStatus.OK).body(SavingsGoalMapper.toResponseList(savingsGoal))
    }

    @PutMapping("/{savingsId}")
    fun update(
        @PathVariable("savingsId") savingsId: Long,
        @RequestBody request: SavingsGoalRequest
    ): ResponseEntity<SavingsGoalResponse> {

        if (savingsId <= 0) {
            throw ResourceNotFoundException("Id inexistente")
        }

        val savingsGoal = service.findById(savingsId)
            .orElseThrow { ResourceNotFoundException("Recurso não encontrado") }

        savingsGoal.goalName = request.goalName
        savingsGoal.targetAmount = request.targetAmount
        savingsGoal.currentAmount = request.currentAmount
        savingsGoal.targetDate = request.targetDate

        val response =
            SavingsGoalMapper.toResponse(service.updateSavingsGoal(savingsGoal))

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @DeleteMapping("/{savingId}")
    fun delete(@PathVariable("savingId") savingId: Long): ResponseEntity<Unit> {

        if (savingId <= 0) {
            throw ResourceNotFoundException("Id inexistente")
        }

        val savings = service.findById(savingId).orElseThrow {
            ResourceNotFoundException("Recurso não encontrado")
        }

        service.deleteSavingsGoal(savings)

        return ResponseEntity.noContent().build()
    }
}