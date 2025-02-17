package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.inbound.controller.ExpenseCategoryController
import com.lagotech.fintrack.adapters.outbound.repository.ExpenseCategoryRepository
import com.lagotech.fintrack.application.dto.ExpenseCategoryDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.ExpenseCategory
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExpenseCategoryService(
    private val repository: ExpenseCategoryRepository,
    private val entityToDTOMapper: EntityToDTOMapper,
    private val messageSource: MessageSource,
    private val assembler: PagedResourcesAssembler<ExpenseCategoryDTO>
) {

    fun save(categoryDTO: ExpenseCategoryDTO): ExpenseCategoryDTO {

        if (existsByName(categoryDTO.name)) {
            throw IllegalArgumentException(
                messageSource.getMessage(
                    "{generic.validation.already.exists}",
                    arrayOf(categoryDTO.name),
                    LocaleContextHolder.getLocale()
                )
            )
        }

        val entity = entityToDTOMapper.parseObject(categoryDTO, ExpenseCategory::class.java)
        val savedExpenseCategory = repository.save(entity)

        return entityToDTOMapper.parseObject(savedExpenseCategory, ExpenseCategoryDTO::class.java)
    }

    fun findByName(name: String): List<ExpenseCategoryDTO> {

        val expenseCategory = repository.findByNameContaining(name)

        if (expenseCategory.isEmpty()) {
            throw ResourceNotFoundException(
                messageSource.getMessage(
                    "{generic.validation.resource.notFound}",
                    null,
                    LocaleContextHolder.getLocale()
                )
            )
        }

        return entityToDTOMapper.parseListObjects(expenseCategory, ExpenseCategoryDTO::class.java)
    }

    fun findAll(pageable: Pageable): PagedModel<EntityModel<ExpenseCategoryDTO>> {

        val expenseCategoryList = repository.findAll(pageable)

        if (expenseCategoryList.isEmpty) {
            throw ResourceNotFoundException(
                messageSource.getMessage(
                    "{generic.validation.resource.notFound}",
                    null,
                    LocaleContextHolder.getLocale()
                )
            )
        }

        val categoriesPage: Page<ExpenseCategoryDTO> = expenseCategoryList.map { cat ->
            entityToDTOMapper.parseObject(cat, ExpenseCategoryDTO::class.java)
        }
        categoriesPage.map { cat -> cat.add(linkTo(ExpenseCategoryController::class.java).slash(cat.id).withSelfRel()) }

        return assembler.toModel(categoriesPage)
    }

    fun findById(id: Long): Optional<ExpenseCategoryDTO> {
        return repository.findById(id)
            .map { entityToDTOMapper.parseObject(it, ExpenseCategoryDTO::class.java) }
    }

    fun existsByName(name: String): Boolean {
        return repository.existsByName(name)
    }

    fun update(id: Long, categoryDTO: ExpenseCategoryDTO): ExpenseCategoryDTO {
        val existingCategory = repository.findById(id)
            .orElseThrow {
                ResourceNotFoundException(
                    messageSource.getMessage(
                        "{generic.validation.entity.id.notFound}",
                        arrayOf(id),
                        LocaleContextHolder.getLocale()
                    )
                )
            }

        existingCategory.name = categoryDTO.name
        existingCategory.description = categoryDTO.description
        existingCategory.color = categoryDTO.color

        val savedCategory = repository.save(existingCategory)

        return entityToDTOMapper.parseObject(savedCategory, ExpenseCategoryDTO::class.java)
    }

    fun delete(id: Long) {
        val category = repository.findById(id)
            .orElseThrow {
                ResourceNotFoundException(
                    messageSource.getMessage(
                        "{generic.validation.entity.id.notFound}",
                        arrayOf(id),
                        LocaleContextHolder.getLocale()
                    )
                )
            }

        repository.delete(category)
    }
}