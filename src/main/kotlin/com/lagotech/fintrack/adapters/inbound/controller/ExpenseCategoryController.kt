package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.domain.service.ExpenseCategoryService
import jakarta.validation.Valid
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
class ExpenseCategoryController(private val service: ExpenseCategoryService) {

    @PostMapping
    fun createCategory(@Valid @RequestBody category: ExpenseCategoryDTO): ResponseEntity<ExpenseCategoryDTO> {
        val createdCategory =
            service.save(category).add(linkTo(ExpenseCategoryController::class.java).slash(category.id).withSelfRel())
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<ExpenseCategoryDTO>> {

        val categories = service.findAll()

        categories.forEach { categoriesDTO ->
            categoriesDTO.add(linkTo(ExpenseCategoryController::class.java).slash(categoriesDTO.id).withSelfRel())
        }
        return ResponseEntity.status(HttpStatus.OK).body(categories)
    }

    @GetMapping("/{categoryId}")
    fun findById(@PathVariable("categoryId") categoryId: Long): ResponseEntity<ExpenseCategoryDTO> {

        val category = service.findById(categoryId)

        val response = category.add(linkTo(ExpenseCategoryController::class.java).slash(category.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

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

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable("categoryId") categoryId: Long): ResponseEntity<Unit> {
        if (categoryId <= 0) {
            throw IllegalArgumentException("ID must be greater than zero")
        }

        service.delete(categoryId)

        return ResponseEntity.noContent().build()
    }
}