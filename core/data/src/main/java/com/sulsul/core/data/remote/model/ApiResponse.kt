package com.sulsul.core.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val resultCode: String,
    val resultMessage: String,
    val resultData: T?
)
