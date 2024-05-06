package com.sulsul.core.data.remote.repository

import com.sulsul.core.data.remote.api.RecordApi
import com.sulsul.core.data.remote.mapper.toRemoteModel
import com.sulsul.core.data.remote.mapper.toStateModel
import com.sulsul.core.model.DrinkRecord
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecordRemoteRepository @Inject constructor(
    private val recordApi: RecordApi
) {
    suspend fun postDrinkRecord(drinkRecord: DrinkRecord) = flow {
        val response = recordApi.postDrinkRecord(drinkRecord.toRemoteModel())
        emit(response)
    }

    suspend fun postStateRecord(drinkRecord: DrinkRecord) = flow {
        val response = recordApi.postStateRecord(drinkRecord.toStateModel())
        emit(response)
    }
}
