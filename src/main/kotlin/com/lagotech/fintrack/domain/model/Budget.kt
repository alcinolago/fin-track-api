package com.lagotech.fintrack.domain.model

import jakarta.persistence.*
import org.hibernate.Hibernate
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "budgets")
class Budget(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgets_seq")
    @SequenceGenerator(name = "budgets_seq", sequenceName = "budgets_seq", allocationSize = 1)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: TransactionCategory,

    @Column(name = "budget_limit", nullable = false)
    var budgetLimit: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var startDate: LocalDateTime?,

    @Column(nullable = false)
    var endDate: LocalDateTime?
) {
    constructor() : this(
        id = null,
        user = User(),
        category = TransactionCategory(),
        budgetLimit = BigDecimal.ZERO,
        startDate = null,
        endDate = null
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Budget

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "Budget(id = $id , user=$user, category=$category, budgetLimit=$budgetLimit, startDate=$startDate, endDate=$endDate)"
    }
}