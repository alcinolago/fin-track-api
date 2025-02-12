package com.lagotech.fintrack.application.dto

import java.time.LocalDateTime

data class ExpenseCategoryDTO(
    var id: Long = 0,
    var name: String,
    var description: String? = null,
    var color: String,
    var createdAt: LocalDateTime
) {
    constructor() : this(0, "", "", "", LocalDateTime.now())
}