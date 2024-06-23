package com.sulsul.core.data.remote.repository

import com.sulsul.core.data.api.SettingApi
import com.sulsul.core.data.remote.model.ApiResponse
import com.sulsul.core.data.remote.model.response.UserInfoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingRepository @Inject constructor(
    private val settingApi: SettingApi
) {
    suspend fun getUserInfo(id: String): Flow<UserInfoResponse> {
        return flow {
            emit(settingApi.getUserInfo(id).resultData!!)
        }
    }

    suspend fun postDeleteAccount(id: String): Flow<ApiResponse<String>> {
        return flow {
            emit(settingApi.postDeleteAccount(id))
        }
    }
}
