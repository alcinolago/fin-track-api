package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.domain.service.ExpenseCategoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Categorias", description = "Gerencia as categorias")
@RestController
@RequestMapping("/category")
class ExpenseCategoryController(private val service: ExpenseCategoryService) {

    @Operation(
        summary = "Cadastrar uma nova categoria",
        description = "Salva uma nova categoria no sistema."
    )
    @PostMapping
    fun createCategory(@Valid @RequestBody category: ExpenseCategoryDTO): ResponseEntity<ExpenseCategoryDTO> {
        val createdCategory =
            service.save(category).add(linkTo(ExpenseCategoryController::class.java).slash(category.id).withSelfRel())
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory)
    }

    @Operation(
        summary = "Listar categorias",
        description = "Listar todas as categorias."
    )
    @GetMapping
    fun findAll(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "limit", defaultValue = "2") limit: Int,
        @RequestParam(value = "sort", defaultValue = "asc") sort: String,
    ): ResponseEntity<PagedModel<EntityModel<ExpenseCategoryDTO>>> {

        val sortDirection: Sort.Direction =
            if("desc".equals(sort, ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC
        val pageable: Pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
        val categories = service.findAll(pageable)

        return ResponseEntity.status(HttpStatus.OK).body(categories)
    }

    @Operation(
        summary = "Buscar categoria por ID",
        description = "Retorna uma categoria específica pelo seu ID",
    )
    @GetMapping("/{categoryId}")
    fun findById(@PathVariable("categoryId") categoryId: Long): ResponseEntity<ExpenseCategoryDTO> {

        val category = service.findById(categoryId)
            .orElseThrow { ResourceNotFoundException("Category with id $categoryId not found") }

        val response = category.add(linkTo(ExpenseCategoryController::class.java).slash(category.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @Operation(
        summary = "Atualizar Categoria",
        description = "Atualizar Categoria pelo id informado."
    )
    @PutMapping("/{categoryId}")
    fun updateCategory(
        @PathVariable("categoryId") categoryId: Long,
        @Valid @RequestBody categoryDTO: ExpenseCategoryDTO
    ): ResponseEntity<ExpenseCategoryDTO> {

        if (categoryId <= 0) {
            throw IllegalArgumentException("ID must be provided and greater than zero")
        }

        val updatedCategory = service.update(categoryId, categoryDTO)

        updatedCategory.add(linkTo(methodOn(ExpenseCategoryController::class.java).findById(categoryId)).withSelfRel())

        return ResponseEntity.ok(updatedCategory)
    }

    @Operation(
        summary = "Remover Categoria",
        description = "Remover Categoria pelo id informado."
    )
    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable("categoryId") categoryId: Long): ResponseEntity<Unit> {
        if (categoryId <= 0) {
            throw IllegalArgumentException("ID must be greater than zero")
        }

        service.delete(categoryId)

        return ResponseEntity.noContent().build()
    }
}