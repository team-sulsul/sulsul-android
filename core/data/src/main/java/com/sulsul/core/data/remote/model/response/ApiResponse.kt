package com.sulsul.core.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val resultCode: Int,
    val resultMessage: String,
    val resultData: T?
)
