package com.sulsul.feature.calendar.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun buildDrinkText(drinkType: String, quantity: Int): String {
    return buildString {
        val (bottles, glasses) = splitQuantity(drinkType, quantity)

        append(getDrinkTheme(drinkType).drinkName)

        if (bottles != 0) {
            append(" ${bottles}병")
        }
        if (glasses != 0) {
            append(" ${glasses}잔")
        }
    }
}

fun buildQuantityText(drinkType: String, quantity: Int): String {
    return buildString {
        val (bottles, glasses) = splitQuantity(drinkType, quantity)

        if (bottles != 0) {
            append("${bottles}병")
        }
        if (glasses != 0) {
            if (bottles != 0) {
                append(" ")
            }
            append("${glasses}잔")
        }
    }
}

fun formatDateToString(date: LocalDate): String {
    return date.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.KOREAN))
}
