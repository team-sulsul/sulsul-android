package com.sulsul.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sulsul.core.database.dao.DrinkInfoDao
import com.sulsul.core.database.dao.DrinkRecordDao
import com.sulsul.core.database.model.DrinkInfoEntity
import com.sulsul.core.database.model.DrinkRecordEntity
import com.sulsul.core.database.util.Converters

@Database(
    entities = [
        DrinkRecordEntity::class,
        DrinkInfoEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SulsulDatabase : RoomDatabase() {
    abstract fun drinkRecordDao(): DrinkRecordDao
    abstract fun drinkInfoDao(): DrinkInfoDao
}
