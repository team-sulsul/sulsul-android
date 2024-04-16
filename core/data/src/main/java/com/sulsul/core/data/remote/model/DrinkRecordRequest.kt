package com.sulsul.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DrinkRecordRequest(
    val recordedAt: String,
    @SerialName("beverages")
    val drinks: List<DrinkInfo>,
)
@Serializable
data class DrinkInfo(
    @SerialName("beverage")
    val drinkType: String,
    val quantity: Int
)
