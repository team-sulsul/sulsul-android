package com.sulsul.core.data.remote.repository

import com.sulsul.core.data.api.LoginApi
import com.sulsul.core.data.api.ReportApi
import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.core.data.remote.model.response.LoginResponse
import com.sulsul.core.data.remote.model.response.ReportResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReportRepository @Inject constructor(
    private val reportApi: ReportApi
) {
    suspend fun getReport(requestDate: String, accessToken: String): Flow<ReportResponse> {
        return flow {
            emit(reportApi.getReport(requestDate = requestDate, accessToken = accessToken))
        }
    }
}

