package com.sulsul.core.data.remote.mapper

import com.sulsul.core.data.remote.model.BeverageInfo
import com.sulsul.core.data.remote.model.request.DrinkRecordRequest
import com.sulsul.core.data.remote.model.request.StateRecordRequest
import com.sulsul.core.data.remote.model.request.TotalRecordRequest
import com.sulsul.core.data.remote.model.response.TotalRecordResponse
import com.sulsul.core.model.DrinkInfo
import com.sulsul.core.model.DrinkRecord

fun DrinkRecordRequest.toData(): DrinkRecord = DrinkRecord()

// 석주에게 수정도 요걸로 통일 부탁
fun DrinkRecord.toRemoteDrinkModel(): DrinkRecordRequest =
    DrinkRecordRequest(
        recordedAt = this.recordedAt.toString(), // String으로 전달
        drinks = this.drinks.map {
            it.toRemoteModel()
        }
    )

fun DrinkRecord.toRemoteStateModel(): StateRecordRequest =
    StateRecordRequest(
        recordedAt = this.recordedAt.toString(),
        drunkennessLevel = this.drunkennessLevel
    )

fun DrinkRecord.toRemoteTotalRequestModel(): TotalRecordRequest =
    TotalRecordRequest(
        recordedAt = this.recordedAt.toString(),
        drunkennessLevel = this.drunkennessLevel,
        drinks = this.drinks.map {
            it.toRemoteModel()
        }
    )

fun DrinkRecord.toRemoteTotalResponseModel(): TotalRecordResponse =
    TotalRecordResponse(
        recordedAt = this.recordedAt.toString(),
        drunkennessLevel = this.drunkennessLevel,
        drinks = this.drinks.map {
            it.toRemoteModel()
        }
    )

private fun DrinkInfo.toRemoteModel(): BeverageInfo =
    BeverageInfo(
        drinkType = this.drinkType,
        quantity = this.quantity,
    )
