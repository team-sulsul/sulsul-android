package com.sulsul.core.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private val TAG = "Retrofit"
    private const val BASE_URL = "http://ec2-52-78-29-203.ap-northeast-2.compute.amazonaws.com:9090"

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor(
        json: Json,
    ): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        when {
            !message.isJsonObject() && !message.isJsonArray() ->
                Timber.tag(TAG).d("CONNECTION INFO -> $message")

            else -> kotlin.runCatching {
                json.encodeToString(Json.parseToJsonElement(message))
            }.onSuccess {
                Timber.tag(TAG).d(it)
            }.onFailure {
                Timber.tag(TAG).d(message)
            }
        }
    }.apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(httpLoggingInterceptor)
        .connectTimeout(5, TimeUnit.SECONDS) // 서버 연결 대기 최대 5초
        .readTimeout(5, TimeUnit.SECONDS) // 데이터 읽기 대기 최대 5초
        .writeTimeout(5, TimeUnit.SECONDS) // 데이터 쓰기 대기 최대 5초
        .pingInterval(10, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideConverterFactory(
        json: Json,
    ): Converter.Factory = json
        .asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
}
fun String.isJsonObject(): Boolean = runCatching { JSONObject(this) }.isSuccess
fun String.isJsonArray(): Boolean = runCatching { JSONArray(this) }.isSuccess
