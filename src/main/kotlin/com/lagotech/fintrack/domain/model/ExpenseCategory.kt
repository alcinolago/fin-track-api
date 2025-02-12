package com.lagotech.fintrack.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "expense_categories")
data class ExpenseCategory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "name", nullable = false, length = 100)
    var name: String,

    @Column(name = "description", length = 255)
    var description: String? = null,

    @Column(name = "color", length = 255)
    var color: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(0, "", "", "", LocalDateTime.now())
}