package com.sulsul.core.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class StateRecordRequest(
    val recordedAt: String,
    val drunkenLevel: String,
)
