package com.lagotech.fintrack.application.extension

import com.lagotech.fintrack.adapters.inbound.controller.TransactionCategoryController
import com.lagotech.fintrack.application.request.TransactionCategoryRequest
import com.lagotech.fintrack.application.response.TransactionCategoryResponse
import com.lagotech.fintrack.domain.model.TransactionCategory
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

object TransactionCategoryMapper {

    fun toEntity(request: TransactionCategoryRequest): TransactionCategory {
        return TransactionCategory(
            name = request.name,
            color = request.color
        )
    }

    fun toResponse(transactionCategory: TransactionCategory): TransactionCategoryResponse {

        val response = TransactionCategoryResponse(
            id = transactionCategory.id!!,
            name = transactionCategory.name,
            color = transactionCategory.color
        )

        response.add(linkTo(methodOn(TransactionCategoryController::class.java).findById(transactionCategory.id!!)).withSelfRel())

        return response
    }

    fun toResponseList(categories: List<TransactionCategory>): List<TransactionCategoryResponse> {
        return categories.map { toResponse(it) }
    }
}