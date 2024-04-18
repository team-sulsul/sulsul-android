package com.sulsul.core.data.remote.repository

import com.sulsul.core.data.api.LoginApi
import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.core.data.remote.model.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val loginApi: LoginApi
) {
    suspend fun postLogin(kakaoAccess: String): Flow<LoginResponse> {
        return flow {
            emit(loginApi.postLogin(LoginRequest(kakaoAccess = kakaoAccess)))
        }
    }
}
