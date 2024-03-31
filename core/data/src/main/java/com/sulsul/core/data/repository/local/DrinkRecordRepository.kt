package com.sulsul.core.data.repository.local

import com.sulsul.core.database.dao.DrinkInfoDao
import com.sulsul.core.database.dao.DrinkRecordDao
import com.sulsul.core.database.model.asExternalModel
import com.sulsul.core.database.model.asEntity
import com.sulsul.core.model.DrinkInfo
import com.sulsul.core.model.DrinkRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DrinkRecordRepository @Inject constructor(
    private val recordDao: DrinkRecordDao,
    private val drinkInfoDao: DrinkInfoDao,
) {
    fun getRecordAll(): Flow<List<DrinkRecord>> = recordDao.getRecordAll().map { records ->
        records.map { record ->
            val drinkInfoList = getDrinkInfoList(record.id).firstOrNull() ?: emptyList()
            record.asExternalModel().copy(
                drinks = drinkInfoList
            )
        }
    }

    private fun getDrinkInfoList(recordId: Int): Flow<List<DrinkInfo>> =
        drinkInfoDao.getDrinkInfoListByRecordId(recordId).map { infoList ->
            infoList.map {
                it.asExternalModel()
            }
        }

    suspend fun addDrinkRecord(record: DrinkRecord) {
        recordDao.insertRecord(record = record.asEntity())
        addDrinkInfo(record.drinks)
    }

    private suspend fun addDrinkInfo(drinkInfoList: List<DrinkInfo>) {
        drinkInfoList.forEach { info ->
            drinkInfoDao.insertDrinkInfo(drinkInfo = info.asEntity())
        }
    }
}
