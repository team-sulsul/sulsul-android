package com.sulsul.core.data.remote.model.response

import com.sulsul.core.data.remote.model.BeverageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalRecordResponse(
    @SerialName("recordedAt")
    val recordedAt: String,
    @SerialName("drunkenLevel")
    val drunkennessLevel: String,
    @SerialName("beverages")
    val drinks: List<BeverageInfo>,
)
