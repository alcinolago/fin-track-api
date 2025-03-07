package com.lagotech.fintrack.mocks

import com.lagotech.fintrack.application.dto.BankAccountDTO
import com.lagotech.fintrack.application.mapper.EntityToDTOMapper
import com.lagotech.fintrack.domain.model.BankAccount
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class BankAccountMock(private val entityToDTOMapper: EntityToDTOMapper) {

//    fun mockBankAccountDTO(): BankAccountDTO {
//        return BankAccountDTO(1, "Itau", "1234", "1", "0001", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0))
//    }
//
//    fun mockBankAccountDTOList(): List<BankAccountDTO> {
//        return listOf(
//            BankAccountDTO(1, "Itau", "1234", "1", "0001", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0)),
//            BankAccountDTO(2, "Bradesco", "5678", "2", "5678", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0))
//        )
//    }
//
//    fun mockBankAccount(): BankAccount {
//        return BankAccount(1, "Itau", "1234", "1", "0001", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0))
//    }
//
//    fun mockBankAccountList(): List<BankAccount> {
//        return listOf(
//            BankAccount(1, "Itau", "1234", "1", "0001", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0)),
//            BankAccount(2, "Bradesco", "5678", "2", "5678", LocalDateTime.of(2025, 2, 25, 14, 30, 0, 0))
//        )
//    }
//
//    fun mockEntityToDTO(): BankAccountDTO {
//        return entityToDTOMapper.parseObject(mockBankAccount(), BankAccountDTO::class.java)
//    }
//
//    fun mockDTOToEntity(): BankAccount {
//        return entityToDTOMapper.parseObject(mockBankAccountDTO(), BankAccount::class.java)
//    }
//
//    fun mockEntityListToDTOList(): List<BankAccountDTO> {
//        return entityToDTOMapper.parseListObjects(mockBankAccountList(), BankAccountDTO::class.java)
//    }
//
//    fun mockDTOListToEntityList(): List<BankAccount> {
//        return entityToDTOMapper.parseListObjects(mockBankAccountDTOList(), BankAccount::class.java)
//    }
}