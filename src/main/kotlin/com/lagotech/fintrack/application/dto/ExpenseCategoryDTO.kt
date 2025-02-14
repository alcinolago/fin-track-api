package com.lagotech.fintrack.application.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDateTime

data class ExpenseCategoryDTO(

    var id: Long = 0,

    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 3, max = 50, message = "{category.name.size}")
    var name: String,

    var description: String? = null,

    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Pattern(
        regexp = "^#([A-Fa-f0-9]{6})$",
        message = "{category.color.pattern}"
    )
    var color: String,

    @field:PastOrPresent(message = "{generic.validation.createdAt.pastOrPresent}")
    var createdAt: LocalDateTime = LocalDateTime.now()
) : RepresentationModel<ExpenseCategoryDTO>() {
    constructor() : this(0, "", "", "", LocalDateTime.now())
}