package com.sulsul.core.data.remote.model.request

import com.sulsul.core.data.remote.model.BeverageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalRecordRequest(
    @SerialName("recordedAt")
    val recordedAt: String,
    @SerialName("drunkenLevel")
    val drunkennessLevel: String,
    @SerialName("beverage")
    val drinks: List<BeverageInfo>,
)
