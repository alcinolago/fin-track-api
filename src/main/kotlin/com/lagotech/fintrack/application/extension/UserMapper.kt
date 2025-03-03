package com.lagotech.fintrack.application.extension

import com.lagotech.fintrack.adapters.inbound.controller.UserController
import com.lagotech.fintrack.application.request.UserRequest
import com.lagotech.fintrack.application.response.UserResponse
import com.lagotech.fintrack.domain.model.User
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

object UserMapper {

    fun toEntity(request: UserRequest): User {
        return User(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            phone = request.phone
        )
    }

    fun toResponse(user: User): UserResponse {
        val response = UserResponse(
            id = user.id!!,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            phone = user.phone
        )

        response.add(linkTo(methodOn(UserController::class.java).findById(user.id!!)).withSelfRel())

        return response
    }

    fun toResponseList(users: List<User>): List<UserResponse> {
        return users.map { toResponse(it) }
    }
}