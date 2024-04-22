package com.sulsul.core.data.remote.api

import com.sulsul.core.data.remote.model.DrinkRecordRequest
import com.sulsul.core.data.remote.model.StateRecordRequest
import com.sulsul.core.data.remote.model.TotalRecordRequest
import com.sulsul.core.model.DrinkRecord
import retrofit2.http.Body
import retrofit2.http.POST

interface RecordApi {

    // 술 기록
    @POST("/records/step1")
    fun postDrinkRecord(
        @Body drinks: DrinkRecordRequest
    )

    // 상태 기록 및 수정
    @POST("/records/step2")
    fun postStateRecord(
        @Body state: StateRecordRequest
    )

    // 전체 기록 동기화
    @POST("//records/bulk")
    fun postTotalRecord(
        @Body records: TotalRecordRequest
    )
}
