package com.lagotech.fintrack.application.dto

import jakarta.validation.constraints.*
import java.time.LocalDateTime

data class BankAccountDTO(
    var id: Long? = null,

    @field:NotBlank(message = "Não pode estar em branco")
    var bankName: String,

    @field:NotBlank(message = "Não pode estar em branco")
    @field:Size(min = 6, max = 11, message = "O número da conta deve ter entre 6 e 11 caracteres")
    @field:Pattern(regexp = "\\d+", message = "O número da conta deve conter apenas números")
    var accountNumber: String,

    @field:NotBlank(message = "Não pode estar em branco")
    @field:Size(min = 1, max = 1, message = "O dígito deve ter exatamente 1 caractere")
    @field:Pattern(regexp = "\\d", message = "O dígito da conta deve ser um número")
    var accountDigit: String,

    @field:NotBlank(message = "Não pode estar em branco")
    @field:Size(min = 4, max = 4, message = "O código da agência deve ter exatamente 4 caracteres")
    @field:Pattern(regexp = "\\d{4}", message = "O código da agência deve conter exatamente 4 números")
    var agency: String,

    @field:PastOrPresent(message = "A data de criação não pode estar no futuro")
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(null, "", "", "", "", LocalDateTime.now())
}