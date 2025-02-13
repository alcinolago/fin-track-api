package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.domain.service.ExpenseCategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class ExpenseCategoryController(private val service: ExpenseCategoryService) {

    @PostMapping
    fun createCategory(@Valid @RequestBody category: ExpenseCategoryDTO): ResponseEntity<ExpenseCategoryDTO> {
        val createdCategory = service.save(category)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory)
    }
}