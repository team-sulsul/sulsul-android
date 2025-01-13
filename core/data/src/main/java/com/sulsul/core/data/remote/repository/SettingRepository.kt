package com.sulsul.core.data.remote.repository

import com.sulsul.core.data.api.SettingApi
import com.sulsul.core.data.remote.model.response.ApiResponse
import com.sulsul.core.data.remote.model.response.UserInfoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingRepository @Inject constructor(
    private val settingApi: SettingApi
) {
    suspend fun getUserInfo(): Flow<UserInfoResponse> {
        return flow {
            emit(settingApi.getUserInfo().resultData!!)
        }
    }

    suspend fun postDeleteAccount(): Flow<ApiResponse<String>> {
        return flow {
            emit(settingApi.postDeleteAccount())
        }
    }
}
