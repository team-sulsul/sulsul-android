package com.sulsul.core.data.remote.mapper

import com.sulsul.core.data.remote.model.DrinkInfo
import com.sulsul.core.data.remote.model.DrinkRecordRequest
import com.sulsul.core.data.remote.model.StateRecordRequest
import com.sulsul.core.model.DrinkRecord

fun DrinkRecordRequest.toData(): DrinkRecord = DrinkRecord(

)

fun DrinkRecord.toRemoteModel(): DrinkRecordRequest =
    DrinkRecordRequest(
        recordedAt = this.recordedAt.toString(), // String으로 전달
        drinks = this.drinks.map {
            it.toRemoteModel()
        }
    )

fun DrinkRecord.toStateModel(): StateRecordRequest =
    StateRecordRequest(
        recordedAt = this.recordedAt.toString(),
        drunkenLevel = this.drunkennessLevel
    )

fun com.sulsul.core.model.DrinkInfo.toRemoteModel(): DrinkInfo =
    DrinkInfo(
        drinkType = this.drinkType,
        quantity = this.quantity,
    )

