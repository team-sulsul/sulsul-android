package com.sulsul.core.database.di

import android.content.Context
import androidx.room.Room
import com.sulsul.core.database.SulsulDatabase
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
    fun provideSulsulDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        SulsulDatabase::class.java,
        "sulsul-database"
    ).build()
}
