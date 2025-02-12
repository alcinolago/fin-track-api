package com.lagotech.fintrack.application.exception

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponse(
    @JsonProperty("error") val error: ApiError
)