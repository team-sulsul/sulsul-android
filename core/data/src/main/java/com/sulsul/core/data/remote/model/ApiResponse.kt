package com.sulsul.core.data.remote.model

data class ApiResponse<T>(
    val resultCode: String,
    val resultMessage: String,
    val resultData: T?
)
