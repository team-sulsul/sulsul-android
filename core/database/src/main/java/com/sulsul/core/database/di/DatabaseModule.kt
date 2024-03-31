package com.sulsul.core.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RoomDatabase::class.java,
        "sulsul-database"
    ).build()
}
