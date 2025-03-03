package com.lagotech.fintrack.domain.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.hibernate.Hibernate
import java.math.BigDecimal

@Entity
@Table(name = "bank_accounts")
class BankAccount(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_account_seq")
    @SequenceGenerator(name = "bank_account_seq", sequenceName = "bank_account_seq", allocationSize = 1)
    var id: Long? = null,

    @Column(name = "bank_name", nullable = false, length = 100)
    var bankName: String,

    @Column(name = "account_number", nullable = false, length = 20)
    var accountNumber: String,

    @Column(name = "account_digit", nullable = false, length = 5)
    var accountDigit: String,

    @Column(name = "agency", nullable = false, length = 10)
    var agency: String,

    @Column(nullable = false)
    var balance: BigDecimal = BigDecimal.ZERO,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    var user: User
) {
    constructor() : this(
        id = null,
        bankName = "",
        accountNumber = "",
        accountDigit = "",
        agency = "",
        balance = BigDecimal.ZERO,
        user = User()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as BankAccount

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "BankAccount(id = $id , bankName = $bankName , accountNumber = $accountNumber, accountDigit = $accountDigit, agency = $agency, balance = $balance )"
    }
}