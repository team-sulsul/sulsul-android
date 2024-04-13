package com.sulsul.core.data.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("accessToken") val accessToken: String,
    @SerialName("refreshToken") val refreshToken: String,
    @SerialName("grantType") val grantType: String,
    @SerialName("expiresIn") val expiresIn: Long,
    @SerialName("message") val message: String
)
