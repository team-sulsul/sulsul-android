package com.sulsul.core.data.local.repository

import android.util.Log
import com.sulsul.core.database.dao.DrinkInfoDao
import com.sulsul.core.database.dao.DrinkRecordDao
import com.sulsul.core.database.model.asExternalModel
import com.sulsul.core.database.model.asEntity
import com.sulsul.core.model.DrinkInfo
import com.sulsul.core.model.DrinkRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class RecordRepository @Inject constructor(
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

    suspend fun insertRecord(record: DrinkRecord) {
        val recordId = recordDao.insertRecord(record = record.asEntity()) // 외래키 id 생성
        insertDrinks(recordId, record.drinks)
    }

    suspend fun updateDrinks(recordId: Int, drinks: List<DrinkInfo>) {
        drinkInfoDao.deleteDrinkInfoByRecordId(recordId)
        drinks.forEach { drinkInfo ->
            val drinkInfoEntity = drinkInfo.asEntity(drinkInfo.recordId)
            drinkInfoDao.insertDrinkInfo(drinkInfoEntity)
        }
    }

    suspend fun updateDrunkennessLevel(date: LocalDate, state: String) {
        val drinkRecordEntity = recordDao.getRecordByDate(date) // 날짜에 해당하는 entity를 가져온다
        drinkRecordEntity.let {
            it.drunkennessLevel = state // level값만 변경하여
            recordDao.updateRecord(drinkRecordEntity) // 업데이트 한다 (id 기준 업데이트)
        }
    }

    private suspend fun insertDrinks(recordId: Long, drinks: List<DrinkInfo>) {
        drinks.forEach { drink ->
            val drinkInfoEntity = drink.asEntity(recordId = recordId.toInt())
            drinkInfoDao.insertDrinkInfo(drinkInfo = drinkInfoEntity)
            Log.d("술 데이터 저장", "$recordId, $drinkInfoEntity")

        }
    }

    private fun getDrinkInfoList(recordId: Int): Flow<List<DrinkInfo>> =
        drinkInfoDao.getDrinkInfoListByRecordId(recordId).map { infoList ->
            infoList.map {
                it.asExternalModel()
            }
        }

    suspend fun deleteRecord(date: LocalDate) {
        val drinkRecordEntity = recordDao.getRecordByDate(date) // 날짜에 해당하는 entity를 가져온다
        recordDao.deleteRecord(drinkRecordEntity)
    }

}
