package com.sulsul.core.data.di

import com.sulsul.core.data.TokenManager
import com.sulsul.core.data.di.AuthInterceptor.Companion.AUTHORIZATION
import com.sulsul.core.data.remote.model.response.TokenResponse
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        Timber.tag("checking Auth").d("authenticate, response.code : ${response.code}")
        if (response.code == 401) {
            val body = response.body?.string()
            val tokenResponse = Json.decodeFromString<TokenResponse>(body ?: "")

            Timber.tag("checking Auth").d("resultCode ${tokenResponse.resultCode}")
            if (tokenResponse.resultCode.toInt() == 400) {
                var accessToken = ""
                runBlocking {
                    tokenResponse.resultData?.accessToken?.let { tokenManager.updateTokenData(it) }
                    accessToken = tokenManager.getTokenData().collect{ it.accessToken }.toString()
                }

                if (accessToken.isEmpty()) {
                    return null
                } else {
                    return response.request
                        .newBuilder()
                        .header(AUTHORIZATION, "Bearer $accessToken").build() // 기존 request 다시 요청
                }
            } else if (tokenResponse.resultCode.toInt() == 401) { // todo : 401이면 로그인화면으로 이동
                return null
            } else {
                return null
            }
        } else {
            return null
        }
    }
}
