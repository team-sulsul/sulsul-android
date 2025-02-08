package com.sulsul.core.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("totalBottle")
    val totalBottle: Int,
    @SerialName("totalDrink")
    val totalDrink: Int,
)
