package com.sulsul.core.data.remote.model.request

import com.sulsul.core.data.remote.model.BeverageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinkRecordRequest(
    @SerialName("recordedAt")
    val recordedAt: String,
    @SerialName("beverages")
    val drinks: List<BeverageInfo>,
)
