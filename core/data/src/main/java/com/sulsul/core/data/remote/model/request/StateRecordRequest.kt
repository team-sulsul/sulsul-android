package com.sulsul.core.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StateRecordRequest(
    @SerialName("recordedAt")
    val recordedAt: String,
    @SerialName("drunkenLevel")
    val drunkennessLevel: String,
)
