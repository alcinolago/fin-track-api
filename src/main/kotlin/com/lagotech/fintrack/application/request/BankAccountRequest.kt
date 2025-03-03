package com.lagotech.fintrack.application.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*
import java.math.BigDecimal

data class BankAccountRequest(

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
    @field:Pattern(regexp = "\\d", message = "{generic.validation.mustBeNumber}")
    var accountDigit: String,

    @Schema(description = "Código da agência bancária", example = "1234")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 4, max = 4, message = "{bankAccount.agency.size}")
    @field:Pattern(regexp = "\\d+", message = "{generic.validation.mustBeNumber}")
    var agency: String,

    @Schema(description = "Saldo disponível na conta")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:DecimalMin(value = "0.01", message = "{bankAccount.balance.minValue}")
    var balance: BigDecimal,

    @Schema(description = "Código Identificador do usuário")
    @field:NotNull(message = "{generic.validation.notNull}")
    @field:Positive(message = "{generic.validation.mustBePositive}")
    var userId: Long
)