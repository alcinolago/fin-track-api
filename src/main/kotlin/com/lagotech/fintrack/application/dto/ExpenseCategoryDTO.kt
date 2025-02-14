package com.lagotech.fintrack.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDateTime

@Schema(
    hidden = true,
    contentMediaType = "application/json",
    example = """{
        "name": "Transporte",
        "description": "Transporte",
        "color": "#0000FF"
    }"""
)
data class ExpenseCategoryDTO(

    @Schema(description = "Identificador único da conta bancária", example = "1")
    var id: Long = 0,

    @Schema(description = "Nome da categoria", example = "Lazer")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 3, max = 50, message = "{category.name.size}")
    var name: String,

    @Schema(description = "Nome da categoria", example = "Entretenimento e lazer")
    var description: String? = null,

    @Schema(description = "Cor da categoria em Hexadecimal", example = "#FFFFFF")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Pattern(
        regexp = "^#([A-Fa-f0-9]{6})$",
        message = "{category.color.pattern}"
    )
    var color: String,

    @Schema(description = "Data de criação do registro", example = "2025-02-14T14:00:00")
    @field:PastOrPresent(message = "{generic.validation.createdAt.pastOrPresent}")
    var createdAt: LocalDateTime = LocalDateTime.now()

) : RepresentationModel<ExpenseCategoryDTO>() {
    constructor() : this(0, "", "", "", LocalDateTime.now())
}