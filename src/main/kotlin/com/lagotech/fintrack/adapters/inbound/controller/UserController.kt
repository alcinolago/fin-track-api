package com.lagotech.fintrack.adapters.inbound.controller

import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.extension.UserMapper
import com.lagotech.fintrack.application.request.UserRequest
import com.lagotech.fintrack.application.response.UserResponse
import com.lagotech.fintrack.domain.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Usuários", description = "Gerencia os usuários")
@RestController
@RequestMapping("/user")
class UserController(private val service: UserService) {

    @Operation(summary = "Cadastrar um novo usuário")
    @PostMapping
    fun save(@RequestBody req: UserRequest): ResponseEntity<UserResponse> {

        val user = service.existsByEmail(req.email)

        if (user) {
            throw IllegalArgumentException("Não é possível utilizar o e-mail informando. Tente outro. ")
        }

        val createdUser = service.save(UserMapper.toEntity(req))

        val response = UserMapper.toResponse(createdUser)
            .add(linkTo(UserController::class.java).slash(createdUser.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @Operation(summary = "Listar usuários")
    @GetMapping
    fun findAll(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "limit", defaultValue = "2") limit: Int,
        @RequestParam(value = "sort", defaultValue = "asc") sort: String,
    ): ResponseEntity<PagedModel<EntityModel<UserResponse>>> {

        val sortDirection: Sort.Direction =
            if ("desc".equals(sort, ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC

        val pageable: Pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"))

        val users = service.findAll(pageable)

        return ResponseEntity.ok(users)
    }

    @Operation(summary = "Listar usuário por Id")
    @GetMapping("/{userId}")
    fun findById(@PathVariable("userId") userId: Long): ResponseEntity<UserResponse> {

        val user = service.findById(userId)
            .orElseThrow { ResourceNotFoundException("User with id $userId not found") }

        val response = UserMapper.toResponse(user)
            .add(linkTo(UserController::class.java).slash(user.id).withSelfRel())

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @Operation(summary = "Atualizar Usuário")
    @PutMapping("/{userId}")
    fun update(
        @PathVariable("userId") userId: Long,
        @RequestBody req: UserRequest
    ): ResponseEntity<UserResponse> {

        if (userId <= 0) {
            throw ResourceNotFoundException("Id invalido ou não informado")
        }

        val user = service.findById(userId)
            .orElseThrow { ResourceNotFoundException("Id inexistente") }

        user.firstName = req.firstName
        user.lastName = req.lastName
        user.phone = req.phone
        user.email = req.email

        val response = service.update(user)

        response.add(linkTo(BudgetController::class.java).slash(userId).withSelfRel())

        return ResponseEntity.ok().body(response)
    }

    @Operation(summary = "Apagar usuário")
    @DeleteMapping("/{userId}")
    fun delete(@PathVariable("userId") userId: Long): ResponseEntity<Unit> {

        if (userId <= 0) {
            throw ResourceNotFoundException("Id invalido ou não informado")
        }

        val user = service.findById(userId)
            .orElseThrow { ResourceNotFoundException("Id inexistente") }

        service.delete(user)

        return ResponseEntity.noContent().build()
    }
}