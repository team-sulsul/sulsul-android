package com.sulsul.core.data.remote.repository

import com.sulsul.core.data.api.LoginApi
import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.core.data.remote.model.response.LoginResponse
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi
) : LoginRepository {
    override suspend fun postLogin(kakaoAccess: LoginRequest): LoginResponse {
        return loginApi.postLogin(kakaoAccess)
    }
}
