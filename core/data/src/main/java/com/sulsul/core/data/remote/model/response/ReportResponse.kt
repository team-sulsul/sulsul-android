package com.sulsul.core.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportResponse(
    @SerialName("resultCode") val resultCode: Long,
    @SerialName("resultMessage") val resultMessage: Long,
    @SerialName("resultData") val resultData: ReportResult,
)

@Serializable
data class ReportResult(
    @SerialName("section1") val monthlyDrinkData: MonthlyDrinkData?, // 이달의 음주량
    @SerialName("section2") val recentThreeMonthDrinks: ArrayList<MonthlyDrinkAmount>, // 최근 3개월의 음주량
    @SerialName("section3") val monthlyDrunkenState: MonthlyDrunkenState?, // 이달의 상태
)

@Serializable
data class MonthlyDrinkData(
    @SerialName("totalBottle") val totalBottle: Int,
    @SerialName("totalDrink") val totalDrink: Int,
    @SerialName("onlyDrink") val onlyDrink: Int,
    @SerialName("maxBeverage") val maxBeverage: String,
    @SerialName("maxBeverageBottle") val maxBeverageBottle: Int,
    @SerialName("maxBeverageDrink") val maxBeverageDrink: Int,
    @SerialName("minBeverage") val minBeverage: String,
    @SerialName("minBeverageBottle") val minBeverageBottle: Int,
    @SerialName("minBeverageDrink") val minBeverageDrink: Int,
    @SerialName("beverageInfos") val beverageInfos: ArrayList<BeverageInfo>,
)

@Serializable
data class BeverageInfo(
    @SerialName("name") val name: String,
    @SerialName("bottle") val bottle: Int,
    @SerialName("drink") val drink: Int,
    @SerialName("onlyDrink") val onlyDrink: Int,
)

@Serializable
data class MonthlyDrinkAmount( //
    @SerialName("date") val date: String,
    @SerialName("times") val times: Int
)

@Serializable
data class MonthlyDrunkenState(
    @SerialName("drunkenLevel1Count") val drunkenLevel1Count: Int,
    @SerialName("drunkenLevel2Count") val drunkenLevel2Count: Int,
    @SerialName("drunkenLevel3Count") val drunkenLevel3Count: Int,
    @SerialName("drunkenLevel4Count") val drunkenLevel4Count: Int,
    @SerialName("drunkenLevel5Count") val drunkenLevel5Count: Int
)

