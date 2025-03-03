package com.lagotech.fintrack.application.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*
import org.springframework.hateoas.server.core.Relation

@Relation(collectionRelation = "categories")
data class TransactionCategoryRequest(

    @Schema(description = "Nome da categoria", example = "Lazer")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 3, max = 50, message = "{category.name.size}")
    val name: String,

    @Schema(description = "Cor da categoria em Hexadecimal", example = "#FFFFFF")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Pattern(
        regexp = "^#([A-Fa-f0-9]{6})$",
        message = "{category.color.pattern}"
    )
    val color: String
)