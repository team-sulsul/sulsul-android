package com.sulsul.core.data.remote.repository

import com.sulsul.core.data.api.LoginApi
import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.core.data.remote.model.request.TokenRequest
import com.sulsul.core.data.remote.model.response.LoginResponse
import com.sulsul.core.data.remote.model.response.TokenResponse
import com.sulsul.core.datastore.datasource.TokenPreferenceDataSource
import com.sulsul.core.datastore.model.TokenData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginApi: LoginApi,
) {
    suspend fun postLogin(kakaoAccess: String): Flow<LoginResponse> {
        return flow {
            emit(loginApi.postLogin(LoginRequest(kakaoAccess = kakaoAccess)))
        }
    }

    suspend fun postToken(accessToken: String): Flow<TokenResponse> {
        return flow {
            emit(loginApi.postToken(
                TokenRequest(
                        accessToken = accessToken
                    )
                )
            )
        }
    }
}
