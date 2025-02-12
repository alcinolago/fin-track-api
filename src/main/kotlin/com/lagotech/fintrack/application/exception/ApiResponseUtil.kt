package com.lagotech.fintrack.application.exception

object ApiResponseUtil {
    fun error(value: Int, message: String?, apiError: ApiError): ApiResponse {
        return ApiResponse(apiError)
    }
}