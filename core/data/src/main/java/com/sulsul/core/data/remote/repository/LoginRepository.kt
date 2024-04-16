package com.sulsul.core.data.remote.repository

import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.core.data.remote.model.response.LoginResponse

interface LoginRepository {
    suspend fun postLogin(kakaoAccess: LoginRequest): LoginResponse
}
