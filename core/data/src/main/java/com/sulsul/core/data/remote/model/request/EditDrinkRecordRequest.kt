package com.sulsul.core.data.remote.model.request

import com.sulsul.core.data.remote.model.BeverageInfo
import kotlinx.serialization.Serializable

@Serializable
data class EditDrinkRecordRequest(
    val beverages: List<BeverageInfo>,
    val deleteBeverages: List<String>,
    val recordedAt: String
)
