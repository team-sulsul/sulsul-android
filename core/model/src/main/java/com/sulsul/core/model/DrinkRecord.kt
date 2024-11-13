package com.sulsul.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class DrinkRecord(
    val id: Long = 0L,
    val recordedAt: LocalDate = LocalDate.now(),
    val drunkennessLevel: String = "DRUNKEN_LEVEL_DEFAULT",
    var drinks: List<DrinkInfo> = emptyList()
) : Parcelable
