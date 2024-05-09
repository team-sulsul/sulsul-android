package com.sulsul.core.data.api

import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.core.data.remote.model.response.LoginResponse
import com.sulsul.core.data.remote.model.response.ReportResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ReportApi {
    @GET("/statistics")
    suspend fun getReport(
        @Query("startDate") requestDate: String,
        @Header("Authorization") accessToken: String
    ): ReportResponse
}
