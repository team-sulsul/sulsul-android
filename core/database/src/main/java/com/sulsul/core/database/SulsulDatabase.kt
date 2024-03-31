package com.sulsul.core.database

import androidx.room.Database
import com.sulsul.core.database.dao.DrinkInfoDao
import com.sulsul.core.database.dao.DrinkRecordDao

@Database(
    entities = [
        DrinkRecordDao::class,
        DrinkInfoDao::class
    ],
    version = 1
)
abstract class SulsulDatabase {
    abstract fun drinkRecordDao(): DrinkRecordDao
    abstract fun drinkInfoDao(): DrinkInfoDao
}
