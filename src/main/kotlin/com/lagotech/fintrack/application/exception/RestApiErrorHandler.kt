package com.lagotech.fintrack.application.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class RestApiErrorHandler(
    private val messageSource: MessageSource
) {

    private val logger: Logger = LoggerFactory.getLogger(RestApiErrorHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ApiResponse> {

        if (request.getDescription(false).contains("/swagger") || request.getDescription(false).contains("/v2/api-docs")) {
            throw ex
        }

        logger.error("Validation error occurred", ex)

        val errorMessage = messageSource.getMessage("validation.error", null, request.locale)

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
            HttpStatus.BAD_REQUEST.value(),
            errorMessage,
            apiError
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(
        ex: ResourceNotFoundException,
        request: WebRequest
    ): ResponseEntity<ApiResponse> {

        if (request.getDescription(false).contains("/swagger") || request.getDescription(false).contains("/v2/api-docs")) {
            throw ex
        }

        logger.error("Validation error occurred", ex)

        val errorMessage = messageSource.getMessage("resource.not.found", null, request.locale)

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

        if (request.getDescription(false).contains("/swagger") || request.getDescription(false).contains("/v2/api-docs")) {
            throw ex
        }

        logger.error("Validation error occurred", ex)

        val errorMessage = messageSource.getMessage(
            "error.request.body.missing",
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

        if (request.getDescription(false).contains("/swagger") || request.getDescription(false).contains("/v2/api-docs")) {
            throw ex
        }

        logger.error("Validation error occurred", ex)

        val supportedMethods = ex.supportedHttpMethods?.joinToString() ?: "none"

        val errorMessage = messageSource.getMessage(
            "error.method.not.allowed",
            arrayOf(ex.method, supportedMethods),
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
}