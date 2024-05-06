package com.sulsul.core.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalRecordRequest(
    @SerialName("recordedAt")
    val recordedAt: String,
    @SerialName("drunkenLevel")
    val drunkenLevel: String,
    @SerialName("beverage")
    val drinks: List<DrinkInfo>,
)
