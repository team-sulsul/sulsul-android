package com.sulsul.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class DrinkRecord(
    val id: Int = 0,
    val recordedAt: LocalDate = LocalDate.now(),
    val drunkennessLevel: String = "",
    val drinks: List<DrinkInfo> = emptyList()
) : Parcelable
