package com.sulsul.feature.calendar.utils

import com.sulsul.feature.calendar.enums.DrinkUnitRatio

fun splitQuantity(type: String, quantity: Int): Pair<Int, Int> {
    val ratio = DrinkUnitRatio.valueOf(type).glassPerBottle

    return when {
        ratio != null -> {
            val bottles = quantity / ratio
            val glasses = quantity % ratio
            Pair(bottles, glasses)
        }
        else -> {
            Pair(0, quantity)
        }
    }
}

fun calculateQuantity(type: String, bottles: Int, glasses: Int): Int {
    val ratio = DrinkUnitRatio.valueOf(type).glassPerBottle

    return when {
        ratio != null -> {
            bottles * ratio + glasses
        }
        else -> {
            glasses
        }
    }
}
