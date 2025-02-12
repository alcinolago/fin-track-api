package com.lagotech.fintrack.application.dto

import java.time.LocalDateTime

data class ExpenseCategoryDTO(
    var id: Long? = null,
    var name: String,
    var description: String? = null,
    var color: String,
    var createdAt: LocalDateTime
) {
    constructor() : this(null, "", "", "", LocalDateTime.now())
}