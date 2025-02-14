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
        "bankName":"Nubank",
        "accountNumber": "3180824",
        "accountDigit": "7",
        "agency": "0001"
    }"""
)
data class BankAccountDTO(

    @Schema(description = "Identificador único da conta bancária", example = "1")
    var id: Long? = null,

    @Schema(description = "Nome do banco", example = "Nubank")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    var bankName: String,

    @Schema(description = "Número da conta bancária sem o dígito", example = "123456")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 6, max = 11, message = "{bankAccount.accountNumber.size}")
    @field:Pattern(regexp = "\\d+", message = "{generic.validation.mustBeNumber}")
    var accountNumber: String,

    @Schema(description = "Dígito da conta", example = "7")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 1, max = 1, message = "{bankAccount.accountDigit.size}")
    @field:Pattern(regexp = "\\d", message = "{generic.validation.mustBeNumber}")
    var accountDigit: String,

    @Schema(description = "Código da agência bancária", example = "1234")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 4, max = 4, message = "{bankAccount.agency.size}")
    @field:Pattern(regexp = "\\d+", message = "{generic.validation.mustBeNumber}")
    var agency: String,

    @Schema(description = "Data de criação do registro", example = "2025-02-14T14:00:00")
    @field:PastOrPresent(message = "{generic.validation.createdAt.pastOrPresent}")
    var createdAt: LocalDateTime = LocalDateTime.now()

) : RepresentationModel<BankAccountDTO>() {
    constructor() : this(null, "", "", "", "", LocalDateTime.now())
}