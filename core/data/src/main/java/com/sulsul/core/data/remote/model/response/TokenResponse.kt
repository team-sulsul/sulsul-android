package com.sulsul.core.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    @SerialName("resultCode") val resultCode: Long,
    @SerialName("resultMessage") val resultMessage: String,
    @SerialName("resultData") val resultData: TokenResult
)

@Serializable
data class TokenResult(
    @SerialName("accessToken") val accessToken: String?,
    @SerialName("refreshToken") val refreshToken: String?,
    @SerialName("message") val message: String?

)
