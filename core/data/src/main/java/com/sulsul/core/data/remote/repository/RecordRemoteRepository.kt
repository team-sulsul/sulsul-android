package com.sulsul.core.data.remote.repository

import com.sulsul.core.data.remote.api.RecordApi
import com.sulsul.core.data.remote.mapper.toRemoteDrinkModel
import com.sulsul.core.data.remote.mapper.toRemoteStateModel
import com.sulsul.core.data.remote.mapper.toRemoteTotalRequestModel
import com.sulsul.core.data.remote.model.request.DeleteRecordRequest
import com.sulsul.core.model.DrinkRecord
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecordRemoteRepository @Inject constructor(
    private val recordApi: RecordApi
) {
    suspend fun postDrinkRecord(drinkRecord: DrinkRecord) = flow {
        val response = recordApi.postDrinkRecord(drinkRecord.toRemoteDrinkModel())
        emit(response)
    }

//    suspend fun putDrinkRecord(drinkRecord: DrinkRecord) = flow {
//        val response = recordApi.putDrinkRecord(drinkRecord.toRemoteDrinkModel())
//    }

    suspend fun postStateRecord(drinkRecord: DrinkRecord) = flow {
        val response = recordApi.postStateRecord(drinkRecord.toRemoteStateModel())
        emit(response)
    }

    suspend fun deleteRecord(deletedRecord: DeleteRecordRequest) = flow {
        val response = recordApi.deleteRecord(deletedRecord)
        emit(response)
    }

    suspend fun postTotalRecord(totalRecord: List<DrinkRecord>) = flow {
        val response = recordApi.postTotalRecord(totalRecord.map {
            it.toRemoteTotalRequestModel()
        })
        emit(response)
    }

    suspend fun getTotalRecord() = flow {
        val response = recordApi.getTotalRecord()
        emit(response)
    }
}
