package com.lagotech.fintrack.domain.model

import jakarta.persistence.*
import org.hibernate.Hibernate
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "savings_goals")
class SavingsGoal(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "savings_goals_seq")
    @SequenceGenerator(name = "savings_goals_seq", sequenceName = "savings_goals_seq", allocationSize = 1)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Column(name = "goal_name", nullable = false)
    var goalName: String,

    @Column(name = "target_amount", nullable = false)
    var targetAmount: BigDecimal,

    @Column(name = "current_amount", nullable = false)
    var currentAmount: BigDecimal = BigDecimal.ZERO,

    @Column(name = "target_date", nullable = false)
    var targetDate: LocalDateTime?
) {
    constructor() : this(
        id = null,
        user = User(),
        goalName = "",
        targetAmount = BigDecimal.ZERO,
        currentAmount = BigDecimal.ZERO,
        targetDate = null
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as SavingsGoal

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "SavingsGoal(id = $id, user=$user, goalName=$goalName, targetAmount=$targetAmount, currentAmount=$currentAmount, targetDate=$targetDate)"
    }
}
