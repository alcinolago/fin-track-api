package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.inbound.controller.UserController
import com.lagotech.fintrack.adapters.outbound.repository.UserRepository
import com.lagotech.fintrack.application.extension.UserMapper
import com.lagotech.fintrack.application.response.UserResponse
import com.lagotech.fintrack.domain.model.User
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val repository: UserRepository,
    private val assembler: PagedResourcesAssembler<UserResponse>
) {

    fun save(user: User): User {
        return repository.save(user)
    }

    fun existsByEmail(email: String): Boolean {
        return repository.existsByEmail(email)
    }

    fun findAll(pageable: Pageable): PagedModel<EntityModel<UserResponse>> {

        val users = repository.findAll(pageable)

        val userList =
            users.map { UserMapper.toResponse(it).add(linkTo(UserController::class.java).slash(it.id).withSelfRel()) }

        return assembler.toModel(userList)
    }

    fun findById(id: Long): Optional<User> {
        return repository.findById(id)
    }

    fun update(user: User): UserResponse {
        return UserMapper.toResponse(repository.save(user))
    }

    fun delete(user: User) {
        repository.delete(user)
    }
}