package com.lagotech.fintrack.application.exception

data class ApiError(
    val status: Int,
    val message: String,
    val errors: MutableList<Map<String, Any?>> = mutableListOf()
) {
    fun addValidationError(field: String, rejectedValue: Any?, message: String?) {
        errors.add(
            mapOf(
                "field" to field,
                "rejectedValue" to rejectedValue,
                "message" to message
            )
        )
    }
}