package com.sulsul.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalRecordRequest(
    val recordedAt: String,
    val drunkenLevel: String,
    @SerialName("beverage")
    val drinks: List<DrinkInfo>,
)
