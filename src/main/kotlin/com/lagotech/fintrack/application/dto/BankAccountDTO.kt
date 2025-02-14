package com.lagotech.fintrack.application.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDateTime

data class BankAccountDTO(
    var id: Long? = null,

    @field:NotBlank(message = "{generic.validation.notBlank}")
    var bankName: String,

    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 6, max = 11, message = "{bankAccount.accountNumber.size}")
    @field:Pattern(regexp = "\\d+", message = "{generic.validation.mustBeNumber}")
    var accountNumber: String,

    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 1, max = 1, message = "{bankAccount.accountDigit.size}")
    @field:Pattern(regexp = "\\d", message = "{generic.validation.mustBeNumber}")
    var accountDigit: String,

    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 4, max = 4, message = "{bankAccount.agency.size}")
    @field:Pattern(regexp = "\\d+", message = "{generic.validation.mustBeNumber}")
    var agency: String,

    @field:PastOrPresent(message = "{generic.validation.createdAt.pastOrPresent}")
    var createdAt: LocalDateTime = LocalDateTime.now()
) : RepresentationModel<BankAccountDTO>() {
    constructor() : this(null, "", "", "", "", LocalDateTime.now())
}