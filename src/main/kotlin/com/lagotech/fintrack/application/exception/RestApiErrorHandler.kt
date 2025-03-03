package com.lagotech.fintrack.application.exception

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.context.MessageSource
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest

@Hidden
@ControllerAdvice
class RestApiErrorHandler(private val messageSource: MessageSource) {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ApiResponse> {

        val errorMessage = messageSource.getMessage(
            "errors.validation",
            null,
            request.locale
        ) // Usando o MessageSource para traduzir a mensagem

        val apiError = ApiError(
            HttpStatus.BAD_REQUEST.value(),
            errorMessage
        )

        for (fieldError in ex.bindingResult.fieldErrors) {
            apiError.addValidationError(
                fieldError.field,
                fieldError.rejectedValue,
                fieldError.defaultMessage
            )
        }

        val response = ApiResponseUtil.error(
            value = HttpStatus.BAD_REQUEST.value(),
            message = errorMessage,
            apiError = apiError
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(
        ex: ResourceNotFoundException,
        request: WebRequest
    ): ResponseEntity<ApiResponse> {

        val errorMessage = messageSource.getMessage(
            "exception.resourceNotFound",
            null,
            request.locale
        )

        val apiError = ApiError(
            HttpStatus.NOT_FOUND.value(),
            ex.localizedMessage ?: errorMessage
        )

        val response = ApiResponse(apiError)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(
        ex: HttpMessageNotReadableException,
        request: WebRequest
    ): ResponseEntity<ApiResponse> {

        val errorMessage = messageSource.getMessage(
            "exception.requestBodyMissing",
            null,
            request.locale
        )

        val apiError = ApiError(
            HttpStatus.BAD_REQUEST.value(),
            errorMessage
        )

        val response = ApiResponseUtil.error(
            HttpStatus.BAD_REQUEST.value(),
            errorMessage,
            apiError
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    fun handleMethodNotAllowedException(
        ex: HttpRequestMethodNotSupportedException,
        request: WebRequest
    ): ResponseEntity<ApiResponse> {

        val errorMessage = messageSource.getMessage(
            "exception.methodNotAllowed",
            arrayOf(ex.method, ex.supportedHttpMethods?.joinToString() ?: ""),
            request.locale
        )

        val apiError = ApiError(
            HttpStatus.METHOD_NOT_ALLOWED.value(),
            errorMessage
        )

        val response = ApiResponseUtil.error(
            HttpStatus.METHOD_NOT_ALLOWED.value(),
            errorMessage,
            apiError
        )

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException,
        request: WebRequest
    ): ResponseEntity<ApiResponse> {

        val errorMessage = messageSource.getMessage(
            "exception.badRequest",
            null,
            request.locale
        )

        val apiError = ApiError(
            HttpStatus.BAD_REQUEST.value(),
            errorMessage
        )

        val response = ApiResponse(apiError)

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleDataIntegrityViolationException(
        ex: DataIntegrityViolationException,
        request: WebRequest
    ): ResponseEntity<ApiResponse> {

        val errorMessage = messageSource.getMessage(
            "exception.unique.violation",
            null,
            request.locale
        )

        val apiError = ApiError(
            HttpStatus.BAD_REQUEST.value(),
            errorMessage
        )

        val response = ApiResponse(apiError)

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }
}