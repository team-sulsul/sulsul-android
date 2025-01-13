package com.sulsul.core.data.api

import com.sulsul.core.data.remote.model.response.ApiResponse
import com.sulsul.core.data.remote.model.response.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface SettingApi {
    @GET("api/members/mypage")
    suspend fun getUserInfo(): ApiResponse<UserInfoResponse>

    @POST("api/members/withdraw")
    suspend fun postDeleteAccount(): ApiResponse<String>
}
