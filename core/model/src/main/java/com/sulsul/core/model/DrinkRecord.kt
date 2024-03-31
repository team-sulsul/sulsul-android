package com.sulsul.core.model

import java.time.LocalDate

data class DrinkRecord(
    val id: Int,
    val recordedAt: LocalDate,
    val drunkennessLevel: String,
    val drinks: List<DrinkInfo>
)
