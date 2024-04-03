package com.sulsul.core.data.di

import com.sulsul.core.data.api.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    // Login API
    @Provides
    @Singleton
    fun providesLoginApi( retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

}
