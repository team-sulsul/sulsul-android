package com.sulsul.core.data.di

import com.sulsul.core.data.repository.LoginRepository
import com.sulsul.core.data.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}
