package com.sulsul.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sulsul.core.database.model.DrinkInfoEntity
import com.sulsul.core.model.DrinkInfo

@Dao
interface DrinkInfoDao {
    @Query("SELECT * FROM record_drink WHERE recordId = :recordId")
    fun getDrinkInfoByRecordId(recordId: Int): List<DrinkInfoEntity>

    @Insert
    fun insertDrinkInfo(drinkInfo: DrinkInfo)
}
