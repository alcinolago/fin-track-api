package com.lagotech.fintrack.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import java.time.LocalDateTime

@Entity
@Table(name = "expense_categories")
data class ExpenseCategory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name", nullable = false, length = 100)
    val name: String,

    @Column(name = "description", length = 255)
    val description: String? = null,

    @Column(name = "color", length = 255)
    val color: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)