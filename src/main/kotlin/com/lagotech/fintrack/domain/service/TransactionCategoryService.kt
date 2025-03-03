package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.inbound.controller.TransactionCategoryController
import com.lagotech.fintrack.adapters.outbound.repository.TransactionCategoryRepository
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.extension.TransactionCategoryMapper
import com.lagotech.fintrack.application.response.TransactionCategoryResponse
import com.lagotech.fintrack.domain.model.TransactionCategory
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionCategoryService(
    private val repository: TransactionCategoryRepository,
    private val messageSource: MessageSource,
    private val assembler: PagedResourcesAssembler<TransactionCategoryResponse>
) {

    fun save(category: TransactionCategory): TransactionCategory {

        if (existsByName(category.name)) {
            throw IllegalArgumentException(
                messageSource.getMessage(
                    "{generic.validation.already.exists}",
                    arrayOf(category.name),
                    LocaleContextHolder.getLocale()
                )
            )
        }

        return repository.save(category)
    }

    fun findAll(pageable: Pageable): PagedModel<EntityModel<TransactionCategoryResponse>> {

        val categories = repository.findAll(pageable)

        if (categories.isEmpty) {
            throw ResourceNotFoundException(
                messageSource.getMessage(
                    "{generic.validation.resource.notFound}",
                    null,
                    LocaleContextHolder.getLocale()
                )
            )
        }

        val categoriesList =
            categories.map {
                TransactionCategoryMapper.toResponse(it)
                    .add(linkTo(TransactionCategoryController::class.java).slash(it.id).withSelfRel())
            }

        return assembler.toModel(categoriesList)
    }

    fun findById(id: Long): Optional<TransactionCategory> {
        return repository.findById(id)
    }

    fun existsByName(name: String): Boolean {
        return repository.existsByName(name)
    }

    fun update(category: TransactionCategory): TransactionCategoryResponse {
        return TransactionCategoryMapper.toResponse(repository.save(category))
    }

    fun delete(category: TransactionCategory) {
        repository.delete(category)
    }
}