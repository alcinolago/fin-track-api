package com.lagotech.fintrack.domain.service

import com.lagotech.fintrack.adapters.outbound.repository.BankAccountRepository
import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.BankAccount
import org.springframework.stereotype.Service

@Service
class BankAccountService(
    private val respository: BankAccountRepository,
    private val entityToDTOMapper: EntityToDTOMapper
) {

    fun save(bankAccount: BankAccount): BankAccountDTO {
        return entityToDTOMapper.parseObject(respository.save(bankAccount), BankAccountDTO::class.java)
    }

    fun findByName(name: String): List<BankAccountDTO> {
        return respository.findByNameContaining(name)
            .map { entityToDTOMapper.parseObject(it, BankAccountDTO::class.java) }
    }

    fun findAll(): List<BankAccountDTO> {
        return respository.findAll().let { entityToDTOMapper.parseListObjects(it, BankAccountDTO::class.java) }
    }

    fun findById(id: Long): BankAccountDTO {
        val bankAccount = respository.findById(id)
            .orElseThrow { IllegalArgumentException("Conta bancária com ID $id não encontrada") }


        return entityToDTOMapper.parseObject(bankAccount, BankAccountDTO::class.java)
    }

    fun existsByName(name: String): Boolean {
        return respository.existsByName(name)
    }

    fun delete(bankAccount: BankAccount) {
        respository.delete(bankAccount)
    }
}