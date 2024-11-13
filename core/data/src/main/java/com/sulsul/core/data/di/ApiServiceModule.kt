package com.sulsul.core.data.di

import com.sulsul.core.data.api.LoginApi
import com.sulsul.core.data.api.ReportApi
import com.sulsul.core.data.api.SettingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun providesLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun providesReportApi(retrofit: Retrofit): ReportApi {
        return retrofit.create(ReportApi::class.java)
    }

    // Setting API
    @Provides
    @Singleton
    fun provideSettingApi(retrofit: Retrofit): SettingApi {
        return retrofit.create(SettingApi::class.java)
    }
}
