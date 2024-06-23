package com.sulsul.core.data.api

import com.sulsul.core.data.remote.model.ApiResponse
import com.sulsul.core.data.remote.model.response.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SettingApi {
    @GET("api/member/{id}")
    suspend fun getUserInfo(
        @Path("id") id: String
    ): ApiResponse<UserInfoResponse>

    @POST("api/auth/withdraw/{id}")
    suspend fun postDeleteAccount(
        @Path("id") id: String
    ): ApiResponse<String>
}
