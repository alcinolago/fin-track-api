package com.lagotech.fintrack.domain.model

import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
@Table(name = "transaction_categories")
data class TransactionCategory(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_categories_seq")
    @SequenceGenerator(
        name = "transaction_categories_seq",
        sequenceName = "transaction_categories_seq",
        allocationSize = 1
    )
    var id: Long? = null,

    @Column(name = "name", nullable = false, length = 100)
    var name: String,

    @Column(name = "color", length = 255)
    var color: String,
) {
    constructor() : this(
        null,
        "",
        ""
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TransactionCategory

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "TransactionCategory(id = $id, name=$name, color=$color)"
    }
}