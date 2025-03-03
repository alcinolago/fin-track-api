package com.lagotech.fintrack.application.response

import org.springframework.hateoas.RepresentationModel

data class TransactionCategoryResponse(
    val id: Long,
    val name: String,
    val color: String
) : RepresentationModel<TransactionCategoryResponse>()