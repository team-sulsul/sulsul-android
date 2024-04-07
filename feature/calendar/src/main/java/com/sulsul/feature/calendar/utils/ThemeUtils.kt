package com.sulsul.feature.calendar.utils

import com.sulsul.feature.calendar.enums.DrinkTheme
import com.sulsul.feature.calendar.enums.DrunkenStateTheme

fun getDrinkTheme(value: String): DrinkTheme {
    return DrinkTheme.valueOf(value)
}

fun getDrunkenStateTheme(value: String): DrunkenStateTheme {
    return DrunkenStateTheme.valueOf(value)
}
