package com.sulsul.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sulsul.core.database.model.DrinkRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkRecordDao {
    @Query("SELECT * FROM record")
    fun getRecordAll(): Flow<List<DrinkRecordEntity>>

    @Insert
    fun insertRecord(record: DrinkRecordEntity)
}
