package com.sulsul.core.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    @SerialName("accessToken") val accessToken: String?,
    @SerialName("refreshToken") val refreshToken: String?,
    @SerialName("message") val message: String?
)
