package com.sulsul.core.data.remote.api

import com.sulsul.core.data.remote.model.request.EditDrinkRecordRequest
import com.sulsul.core.data.remote.model.response.ApiResponse
import com.sulsul.core.data.remote.model.request.DrinkRecordRequest
import com.sulsul.core.data.remote.model.request.DeleteRecordRequest
import com.sulsul.core.data.remote.model.request.StateRecordRequest
import com.sulsul.core.data.remote.model.request.TotalRecordRequest
import com.sulsul.core.data.remote.model.response.TotalRecordResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import javax.annotation.Nullable

interface RecordApi {

    // 술 기록
    @POST("/records/step1")
    suspend fun postDrinkRecord(
        @Body drinks: DrinkRecordRequest
    ): ApiResponse<Nullable>

    // 술 기록 수정
    @PUT("/records/step1")
    suspend fun putDrinkRecord(
        @Body drinks: EditDrinkRecordRequest
    ): ApiResponse<Nullable>

    // 상태 기록 및 수정
    @POST("/records/step2")
    suspend fun postStateRecord(
        @Body state: StateRecordRequest
    ): ApiResponse<Nullable>

    // 해당 날짜 기록 삭제
    @DELETE("/records")
    suspend fun deleteRecord(
        @Body date: DeleteRecordRequest
    ): ApiResponse<Nullable>

    // 전체 기록 동기화
    @POST("/records/bulk")
    suspend fun postTotalRecord(
        @Body records: List<TotalRecordRequest>
    ): ApiResponse<Nullable>

    // 전체 기록 조회
    @GET("/records")
    suspend fun getTotalRecord(): ApiResponse<List<TotalRecordResponse>>
}
