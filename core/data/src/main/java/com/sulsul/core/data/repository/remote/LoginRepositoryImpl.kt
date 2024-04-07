package com.sulsul.core.data.repository.remote

import com.sulsul.core.data.api.LoginApi
import com.sulsul.core.data.model.remote.request.LoginRequest
import com.sulsul.core.data.model.remote.response.LoginResponse
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi
) : LoginRepository {
    override suspend fun postLogin(authCode: LoginRequest): LoginResponse {
        return loginApi.postLogin(authCode)
    }
}
