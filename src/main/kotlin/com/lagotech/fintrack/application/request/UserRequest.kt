package com.lagotech.fintrack.application.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*

data class UserRequest(

   @Schema(description = "Primeiro nome do usuário", example = "João")
   @field:NotBlank(message = "{generic.validation.notBlank}")
   val firstName: String,

   @Schema(description = "Último nome do usuário", example = "Silva")
   @field:NotBlank(message = "{generic.validation.notBlank}")
   val lastName: String,

   @Schema(description = "E-mail do usuário", example = "joao.silva@example.com")
   @field:NotBlank(message = "{generic.validation.notBlank}")
   @field:Size(min = 5, max = 100, message = "{generic.validation.size}")
   @field:Email(message = "{generic.validation.invalidEmail}")
   val email: String,

   @Schema(description = "Número de telefone do usuário", example = "+55 11 91234-5678")
   @field:NotBlank(message = "{generic.validation.notBlank}")
   @field:Pattern(
      regexp = "^\\+?\\d{1,3}\\s?\\d{2}\\s?\\d{4,5}-?\\d{4}$",
      message = "{generic.validation.invalidPhone}"
   )
   val phone: String
)