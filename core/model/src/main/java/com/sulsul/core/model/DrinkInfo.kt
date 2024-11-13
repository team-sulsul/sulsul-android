package com.sulsul.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DrinkInfo(
    val recordId: Long = 0L,
    val drinkType: String,
    val quantity: Int
) : Parcelable
