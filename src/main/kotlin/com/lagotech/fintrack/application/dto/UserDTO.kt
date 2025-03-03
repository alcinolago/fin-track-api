package com.lagotech.fintrack.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.hateoas.RepresentationModel

@Schema(description = "Representação do usuário")
class UserDTO(

    @Schema(description = "Identificador único do usuário", example = "1")
    var id: Long? = null,

    @Schema(description = "Primeiro nome do usuário", example = "João")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    var firstName: String,

    @Schema(description = "Último nome do usuário", example = "Silva")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    var lastName: String,

    @Schema(description = "E-mail do usuário", example = "joao.silva@example.com")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    @field:Size(min = 5, max = 100, message = "{generic.validation.notBlank}")
    var email: String,

    @Schema(description = "Número de telefone do usuário", example = "+55 11 91234-5678")
    @field:NotBlank(message = "{generic.validation.notBlank}")
    var phone: String,

    @Schema(description = "Contas bancárias associadas ao usuário")
    var bankAccounts: MutableList<BankAccountDTO>? = mutableListOf()

) : RepresentationModel<UserDTO>() {
    constructor() : this(id = 0, firstName = "", lastName = "", email = "", phone = "", bankAccounts = mutableListOf())
}