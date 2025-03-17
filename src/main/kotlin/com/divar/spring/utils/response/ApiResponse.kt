package com.divar.spring.utils.response

import org.springframework.http.ResponseEntity

object ApiResponse {
    fun <T> success(data: T, message: String = ""): ResponseEntity<*> {
        val successResponse = SuccessResponse(data = data, message = message)
        return ResponseEntity.ok(successResponse)
    }

    fun error(apiError: ApiError): ResponseEntity<FailureResponse> {
        val failureResponse = FailureResponse(
            message = apiError.message,
            errorCode = apiError.httpStatus.value()
        )
        return ResponseEntity.status(apiError.httpStatus.value()).body(failureResponse)
    }
}

enum class Status { Success, Failure }

data class SuccessResponse<T>(
    val data: T?,
    val status: Status = Status.Success,
    val message: String = "",
)

data class FailureResponse(
    val status: Status = Status.Failure,
    val message: String = "",
    val errorCode: Int,
)