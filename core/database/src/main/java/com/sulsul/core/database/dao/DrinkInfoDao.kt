package com.sulsul.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sulsul.core.database.model.DrinkInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkInfoDao {
    @Query("SELECT * FROM record_drink WHERE recordId = :recordId")
    fun getDrinkInfoListByRecordId(recordId: Int): Flow<List<DrinkInfoEntity>>

    @Insert
    suspend fun insertDrinkInfo(drinkInfo: DrinkInfoEntity)

    @Query("DELETE FROM record_drink WHERE recordId = :recordId")
    suspend fun deleteDrinkInfoByRecordId(recordId: Int) // recordId가 동일한 모든 drinkInfo 삭제
}
