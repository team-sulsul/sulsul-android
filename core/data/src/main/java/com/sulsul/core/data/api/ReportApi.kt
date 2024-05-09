package com.sulsul.core.data.api

import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.core.data.remote.model.response.LoginResponse
import com.sulsul.core.data.remote.model.response.ReportResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ReportApi {
    @GET("/api/statistics?startDate={startDate}")
    suspend fun getReport(
        @Path("startDate") requestDate: String,
        @Header("Authorization") accessToken: String
    ): ReportResponse
}
