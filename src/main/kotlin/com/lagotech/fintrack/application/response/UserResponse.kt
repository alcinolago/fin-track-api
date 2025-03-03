package com.lagotech.fintrack.application.response

import org.springframework.hateoas.RepresentationModel

data class UserResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String
) : RepresentationModel<UserResponse>()