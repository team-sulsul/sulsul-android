package com.sulsul.core.data.api

import com.sulsul.core.data.model.remote.request.LoginRequest
import com.sulsul.core.data.model.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("/api/auth/kakao")
    suspend fun postLogin(
        @Body kakaoAccess: LoginRequest
    ): LoginResponse
}
