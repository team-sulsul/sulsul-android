package com.sulsul.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeverageInfo(
    @SerialName("beverage")
    val drinkType: String,
    @SerialName("quantity")
    val quantity: Int
)
