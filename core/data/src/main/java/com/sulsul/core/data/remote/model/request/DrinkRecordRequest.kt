package com.sulsul.core.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DrinkRecordRequest(
    @SerialName("recordedAt")
    val recordedAt: String,
    @SerialName("beverages")
    val drinks: List<DrinkInfo>,
)
@Serializable
data class DrinkInfo(
    @SerialName("beverage")
    val drinkType: String,
    @SerialName("quantity")
    val quantity: Int
)
