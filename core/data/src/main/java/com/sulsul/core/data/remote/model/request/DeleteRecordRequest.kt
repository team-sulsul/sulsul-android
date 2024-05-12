package com.sulsul.core.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteRecordRequest(
    @SerialName("recordedAt")
    val recordedAt: String
) // mapper 없이 사용
