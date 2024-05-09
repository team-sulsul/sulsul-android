package com.sulsul.core.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("resultCode") val resultCode: Long,
    @SerialName("resultMessage") val resultMessage: String,
    @SerialName("resultData") val resultData: LoginResult
)

@Serializable
data class LoginResult(
    @SerialName("accessToken") val accessToken: String,
    @SerialName("refreshToken") val refreshToken: String,
    @SerialName("grantType") val grantType: String,
    @SerialName("expiresIn") val expiresIn: Long,
    @SerialName("message") val message: String
)

