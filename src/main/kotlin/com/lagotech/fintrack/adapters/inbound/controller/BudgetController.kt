package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.extension.BudgetMapper
import com.lagotech.fintrack.application.request.BudgetRequest
import com.lagotech.fintrack.application.response.BudgetResponse
import com.lagotech.fintrack.domain.service.BudgetService
import com.lagotech.fintrack.domain.service.TransactionCategoryService
import com.lagotech.fintrack.domain.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Orçamento", description = "Gerenciar orçamento")
@RestController
@RequestMapping("/budget")
class BudgetController(
    private val service: BudgetService,
    private val userService: UserService,
    private val categoryService: TransactionCategoryService
) {

    @Operation(summary = "Cadastrar um novo orçamento")
    @PostMapping
    fun save(@RequestBody req: BudgetRequest): ResponseEntity<BudgetResponse> {

        val user = userService.findById(req.userId)
            .orElseThrow { ResourceNotFoundException("") }

        val category = categoryService.findById(req.categoryId)
            .orElseThrow { ResourceNotFoundException("") }

        val response = service.save(BudgetMapper.toEntity(req, user, category))

        return ResponseEntity.status(HttpStatus.CREATED).body(BudgetMapper.toResponse(response))
    }

    @Operation(summary = "Listar orçamentos")
    @GetMapping("/users/{userId}/budgets")
    fun findByUserId(@PathVariable("userId") userId: Long): ResponseEntity<List<BudgetResponse>> {

        if (userId <= 0) {
            throw ResourceNotFoundException("Id invalido")
        }

        val budgets = service.findByUserId(userId)

        if (budgets.isEmpty()) {
            return ResponseEntity.noContent().build()
        }

        return ResponseEntity.ok().body(BudgetMapper.toResponseList(budgets))
    }

    @Operation(summary = "Buscar orçamento por ID")
    @GetMapping("/{budgetId}")
    fun findById(@PathVariable("budgetId") budgetId: Long): ResponseEntity<BudgetResponse> {
        if (budgetId <= 0) {
            throw ResourceNotFoundException("Id invalido")
        }

        val budget = service.findById(budgetId)
            .orElseThrow { ResourceNotFoundException("Id não encontrado") }

        val response = BudgetMapper
            .toResponse(budget)
            .add(linkTo(BudgetController::class.java).slash(budgetId).withSelfRel())

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @Operation(summary = "Atualizar Orçamento")
    @PutMapping("/{budgetId}")
    fun update(
        @PathVariable("budgetId") budgetId: Long,
        @RequestBody req: BudgetRequest
    ): ResponseEntity<BudgetResponse> {

        if (budgetId <= 0) {
            throw ResourceNotFoundException("Id invalido ou não informado")
        }

        val budget = service.findById(budgetId)
            .orElseThrow { ResourceNotFoundException("Id inexistente") }

        budget.budgetLimit = req.budgetLimit
        budget.startDate = req.startDate
        budget.endDate = req.endDate
        budget.category.id = req.categoryId

        val response = service.update(budget)

        response.add(linkTo(BudgetController::class.java).slash(budgetId).withSelfRel())

        return ResponseEntity.ok().body(response)
    }

    @Operation(summary = "Apagar Orçamento")
    @DeleteMapping("/{budgetId}")
    fun delete(@PathVariable("budgetId") budgetId: Long): ResponseEntity<Unit> {

        if (budgetId <= 0) {
            throw ResourceNotFoundException("Id invalido ou não informado")
        }

        val budget = service.findById(budgetId)
            .orElseThrow { ResourceNotFoundException("Id inexistente") }

        service.delete(budget)

        return ResponseEntity.noContent().build()
    }
}