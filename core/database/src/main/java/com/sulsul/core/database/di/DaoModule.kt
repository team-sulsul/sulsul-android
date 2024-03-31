package com.sulsul.core.database.di

import androidx.room.RoomDatabase
import com.sulsul.core.database.SulsulDatabase
import com.sulsul.core.database.dao.DrinkInfoDao
import com.sulsul.core.database.dao.DrinkRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesDrinkRecordDao(
        database: SulsulDatabase
    ): DrinkRecordDao = database.drinkRecordDao()

    @Provides
    fun providesDrinkInfoDao(
        database: SulsulDatabase
    ): DrinkInfoDao = database.drinkInfoDao()
}
