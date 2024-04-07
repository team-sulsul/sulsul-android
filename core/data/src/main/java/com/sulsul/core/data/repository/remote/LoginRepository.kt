package com.sulsul.core.data.repository.remote

import com.sulsul.core.data.model.remote.request.LoginRequest
import com.sulsul.core.data.model.remote.response.LoginResponse

interface LoginRepository {
    suspend fun postLogin(authCode: LoginRequest): LoginResponse
}
