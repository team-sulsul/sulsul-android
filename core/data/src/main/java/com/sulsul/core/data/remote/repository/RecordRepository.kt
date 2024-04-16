package com.sulsul.core.data.remote.repository

import com.sulsul.core.data.remote.api.RecordApi
import com.sulsul.core.data.remote.mapper.toRemoteModel
import com.sulsul.core.data.remote.mapper.toStateModel
import com.sulsul.core.model.DrinkRecord
import javax.inject.Inject

class RecordRepository @Inject constructor(
    private val recordApi: RecordApi
) {
    fun postDrinkRecord(drinkRecord: DrinkRecord) {
        recordApi.postDrinkRecord(drinkRecord.toRemoteModel())
    }

    fun postStateRecord(drinkRecord: DrinkRecord) {
        recordApi.postStateRecord(drinkRecord.toStateModel())
    }
}
