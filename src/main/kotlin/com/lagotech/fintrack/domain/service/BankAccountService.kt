package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.BankAccountRepository
import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.exception.ResourceNotFoundException
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.BankAccount
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class BankAccountService(
    private val repository: BankAccountRepository,
    private val entityToDTOMapper: EntityToDTOMapper,
    private val messageSource: MessageSource
) {

    fun save(bankAccount: BankAccountDTO): BankAccountDTO {

        if (existsByBankName(bankAccount.bankName)) {
            throw IllegalArgumentException(
                messageSource.getMessage(
                    "{generic.validation.already.exists}",
                    arrayOf(bankAccount.bankName),
                    LocaleContextHolder.getLocale()
                )
            )
        }

        val entity = entityToDTOMapper.parseObject(bankAccount, BankAccount::class.java)
        val savedBankAccount = repository.save(entity)

        return entityToDTOMapper.parseObject(savedBankAccount, BankAccountDTO::class.java)
    }

    fun findByName(name: String): List<BankAccountDTO> {
        val bankAccounts = repository.findByBankNameContaining(name)

        if (bankAccounts.isEmpty()) {
            throw ResourceNotFoundException(
                messageSource.getMessage(
                    "{generic.validation.resource.notFound}", null, LocaleContextHolder.getLocale()
                )
            )
        }
        return entityToDTOMapper.parseListObjects(bankAccounts, BankAccountDTO::class.java)
    }

    fun findAll(): List<BankAccountDTO> {

        val bankAccopunts = repository.findAll()

        if (bankAccopunts.isEmpty()) {
            throw ResourceNotFoundException(
                messageSource.getMessage(
                    "{generic.validation.resource.notFound}", null, LocaleContextHolder.getLocale()
                )
            )
        }
        return entityToDTOMapper.parseListObjects(bankAccopunts, BankAccountDTO::class.java)
    }

    fun findById(id: Long): Optional<BankAccountDTO> {
        return repository.findById(id).map { entityToDTOMapper.parseObject(it, BankAccountDTO::class.java) }
    }

    fun existsByBankName(name: String): Boolean {
        return repository.existsByBankName(name)
    }

    fun update(id: Long, bankAccountDTO: BankAccountDTO): BankAccountDTO {
        val existingAccount = repository.findById(id).orElseThrow {
                ResourceNotFoundException(
                    messageSource.getMessage(
                        "{generic.validation.entity.id.notFound}", arrayOf(id), LocaleContextHolder.getLocale()
                    )
                )
            }

        existingAccount.bankName = bankAccountDTO.bankName
        existingAccount.accountNumber = bankAccountDTO.accountNumber
        existingAccount.accountDigit = bankAccountDTO.accountDigit
        existingAccount.accountDigit = bankAccountDTO.accountDigit
        existingAccount.agency = bankAccountDTO.agency

        val savedAccountBank = repository.save(existingAccount)

        return entityToDTOMapper.parseObject(savedAccountBank, BankAccountDTO::class.java)
    }

    fun delete(id: Long) {
        val bank = repository.findById(id).orElseThrow {
                ResourceNotFoundException(
                    messageSource.getMessage(
                        "{generic.validation.entity.id.notFound}", arrayOf(id), LocaleContextHolder.getLocale()
                    )
                )
            }

        repository.delete(bank)
    }
}