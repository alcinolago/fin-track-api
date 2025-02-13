package com.lagotech.fintrack.application.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class ExpenseCategoryDTO(
    var id: Long? = null,

    @field:NotBlank(message = "Não pode estar em branco")
    @field:Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    var name: String,

    var description: String? = null,

    @field:NotBlank(message = "Não pode estar em branco")
    @field:Pattern(
        regexp = "^#([A-Fa-f0-9]{6})$",
        message = "A cor deve estar no formato hexadecimal (ex: #FFFFFF)"
    )
    var color: String,

    @field:PastOrPresent(message = "A data de criação não pode estar no futuro")
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(null, "", "", "", LocalDateTime.now())
}