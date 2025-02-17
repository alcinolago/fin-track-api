//package com.lagotech.fintrack.infrastructure.config
//
//import com.lagotech.fintrack.adapters.outbound.repository.BankAccountRepository
//import com.lagotech.fintrack.adapters.outbound.repository.ExpenseCategoryRepository
//import com.lagotech.fintrack.domain.model.BankAccount
//import com.lagotech.fintrack.domain.model.ExpenseCategory
//import org.springframework.boot.CommandLineRunner
//import org.springframework.stereotype.Component
//
//@Component
//class DatabaseSeeder(
//    private val categoryRepository: ExpenseCategoryRepository,
//    private val bankRepository: BankAccountRepository
//) : CommandLineRunner {
//
//    override fun run(vararg args: String?) {
//        seedCategories()
//        seedBanks()
//    }
//
//    private fun seedCategories() {
//        if (categoryRepository.count() == 0L) {
//            val categories = listOf(
//                ExpenseCategory(name = "Alimentação", description = "Despesas com comida", color = "#FF5733"),
//                ExpenseCategory(name = "Transporte", description = "Gastos com transporte", color = "#33FF57"),
//                ExpenseCategory(name = "Lazer", description = "Entretenimento e lazer", color = "#3357FF"),
//                ExpenseCategory(name = "Educação", description = "Cursos e materiais de estudo", color = "#FFC300"),
//                ExpenseCategory(name = "Saúde", description = "Gastos médicos", color = "#DAF7A6"),
//                ExpenseCategory(name = "Moradia", description = "Aluguel, contas de casa", color = "#C70039"),
//                ExpenseCategory(name = "Seguros", description = "Planos de seguro", color = "#900C3F"),
//                ExpenseCategory(name = "Investimentos", description = "Aportes financeiros", color = "#581845"),
//                ExpenseCategory(name = "Doações", description = "Caridade e doações", color = "#28A745"),
//                ExpenseCategory(name = "Outros", description = "Gastos diversos", color = "#6C757D")
//            )
//            categoryRepository.saveAll(categories)
//            println("✔ 10 categorias inseridas na base!")
//        }
//    }
//
//    private fun seedBanks() {
//        if (bankRepository.count() == 0L) {
//            val banks = listOf(
//                BankAccount(bankName = "Banco do Brasil", accountNumber = "123456", accountDigit = "7", agency = "0001"),
//                BankAccount(bankName = "Caixa Econômica", accountNumber = "654321", accountDigit = "2", agency = "0002"),
//                BankAccount(bankName = "Nubank", accountNumber = "987654", accountDigit = "5", agency = "0003")
//            )
//            bankRepository.saveAll(banks)
//            println("✔ 3 bancos inseridos na base!")
//        }
//    }
//}
