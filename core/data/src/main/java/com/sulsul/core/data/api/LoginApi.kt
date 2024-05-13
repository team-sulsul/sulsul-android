package com.sulsul.core.data.api

import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.core.data.remote.model.request.TokenRequest
import com.sulsul.core.data.remote.model.response.LoginResponse
import com.sulsul.core.data.remote.model.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {
    @POST("/api/auth/kakao")
    @Headers("Auth: false")
    suspend fun postLogin(
        @Body kakaoAccess: LoginRequest
    ): LoginResponse

    @POST("/api/auth/login")
    @Headers("Auth: false")
    suspend fun postToken(
        @Body sulsulToken: TokenRequest
    ) : TokenResponse
}
