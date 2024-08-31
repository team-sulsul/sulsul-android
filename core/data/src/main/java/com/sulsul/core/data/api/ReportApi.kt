package com.sulsul.core.data.api

import com.sulsul.core.data.remote.model.response.ReportResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ReportApi {
    @GET("/api/statistics")
    suspend fun getReport(
        @Query("startDate") requestDate: String
    ): ReportResponse
}
