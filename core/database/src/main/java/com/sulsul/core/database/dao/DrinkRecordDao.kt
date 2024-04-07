package com.sulsul.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sulsul.core.database.model.DrinkRecordEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface DrinkRecordDao {
    @Query("SELECT * FROM record")
    fun getRecordAll(): Flow<List<DrinkRecordEntity>>

    @Insert
    suspend fun insertRecord(record: DrinkRecordEntity): Long

    @Update
    suspend fun updateRecord(record: DrinkRecordEntity)

    @Query("SELECT * FROM record WHERE recordedAt = :date")
    suspend fun getRecordByDate(date: LocalDate): DrinkRecordEntity
}
