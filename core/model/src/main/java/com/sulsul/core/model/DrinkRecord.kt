package com.sulsul.core.model

import java.time.LocalDate

data class DrinkRecord(
    val id: Int = 0,
    val recordedAt: LocalDate,
    val drunkennessLevel: String,
    val drinks: List<DrinkInfo>
)
