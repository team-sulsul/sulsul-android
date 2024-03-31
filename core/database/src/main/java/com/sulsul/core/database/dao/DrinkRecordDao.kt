package com.sulsul.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sulsul.core.database.model.DrinkRecordEntity

@Dao
interface DrinkRecordDao {
    @Query("SELECT * FROM record")
    fun getDrinkReport()

    @Insert
    fun insertRecord(record: DrinkRecordEntity)
}
