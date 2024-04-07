package com.sulsul.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DrinkInfo(
    val recordId: Int = 0,
    val drinkType: String,
    val quantity: Int
) : Parcelable
