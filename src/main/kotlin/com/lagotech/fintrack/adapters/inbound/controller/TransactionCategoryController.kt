package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.extension.TransactionCategoryMapper
import com.lagotech.fintrack.application.request.TransactionCategoryRequest
import com.lagotech.fintrack.application.response.TransactionCategoryResponse
import com.lagotech.fintrack.domain.service.TransactionCategoryService
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
class TransactionCategoryController(private val service: TransactionCategoryService) {

    @Operation(summary = "Cadastrar uma nova categoria")
    @PostMapping
    fun save(@Valid @RequestBody category: TransactionCategoryRequest): ResponseEntity<TransactionCategoryResponse> {

        val entity =
            service.save(TransactionCategoryMapper.toEntity(category))

        val response = TransactionCategoryMapper.toResponse(entity)
            .add(linkTo(TransactionCategoryController::class.java).slash(entity.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @Operation(summary = "Listar categorias")
    @GetMapping
    fun findAll(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "limit", defaultValue = "2") limit: Int,
        @RequestParam(value = "sort", defaultValue = "asc") sort: String,
    ): ResponseEntity<PagedModel<EntityModel<TransactionCategoryResponse>>> {

        val sortDirection: Sort.Direction =
            if ("desc".equals(sort, ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC

        val pageable: Pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"))

        val categories = service.findAll(pageable)

        return ResponseEntity.status(HttpStatus.OK).body(categories)
    }

    @Operation(summary = "Buscar categoria por ID")
    @GetMapping("/{categoryId}")
    fun findById(@PathVariable("categoryId") categoryId: Long): ResponseEntity<TransactionCategoryResponse> {

        val category = service.findById(categoryId)
            .orElseThrow { ResourceNotFoundException("Category with id $categoryId not found") }

        val response = TransactionCategoryMapper.toResponse(category)
            .add(linkTo(TransactionCategoryController::class.java).slash(category.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @Operation(summary = "Atualizar Categoria")
    @PutMapping("/{categoryId}")
    fun update(
        @PathVariable("categoryId") categoryId: Long,
        @Valid @RequestBody request: TransactionCategoryRequest
    ): ResponseEntity<TransactionCategoryResponse> {

        if (categoryId <= 0) {
            throw IllegalArgumentException("ID must be provided and greater than zero")
        }

        val category = service.findById(categoryId)
            .orElseThrow { ResourceNotFoundException("Id inexistente") }


        category.name = request.name
        category.color = request.color

        val response = service.update(category)

        response.add(linkTo(methodOn(TransactionCategoryController::class.java).findById(categoryId)).withSelfRel())

        return ResponseEntity.ok(response)
    }

    @Operation(summary = "Remover Categoria")
    @DeleteMapping("/{categoryId}")
    fun delete(@PathVariable("categoryId") categoryId: Long): ResponseEntity<Unit> {

        if (categoryId <= 0) {
            throw ResourceNotFoundException("ID must be greater than zero")
        }

        val category = service.findById(categoryId)
            .orElseThrow { ResourceNotFoundException("Id inexistente") }

        service.delete(category)

        return ResponseEntity.noContent().build()
    }
}