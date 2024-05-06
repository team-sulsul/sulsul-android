package com.sulsul.core.data.remote.api

import com.sulsul.core.data.remote.model.ApiResponse
import com.sulsul.core.data.remote.model.DrinkRecordRequest
import com.sulsul.core.data.remote.model.StateRecordRequest
import com.sulsul.core.data.remote.model.TotalRecordRequest
import retrofit2.http.Body
import retrofit2.http.POST
import javax.annotation.Nullable

interface RecordApi {

    // 술 기록
    @POST("/records/step1")
    suspend fun postDrinkRecord(
        @Body drinks: DrinkRecordRequest
    ): ApiResponse<Nullable>

    // 상태 기록 및 수정
    @POST("/records/step2")
    suspend fun postStateRecord(
        @Body state: StateRecordRequest
    ): ApiResponse<Nullable>

    // 전체 기록 동기화
    @POST("/records/bulk")
    suspend fun postTotalRecord(
        @Body records: TotalRecordRequest
    ): ApiResponse<Nullable>
}
