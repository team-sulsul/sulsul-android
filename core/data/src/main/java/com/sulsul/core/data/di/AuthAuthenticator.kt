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
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == 401) {
            val body = response.body?.string()
            val tokenResponse = Json.decodeFromString<TokenResponse>(body ?: "")

            if (tokenResponse.resultCode.toInt() == 400) { // access만료, 토큰 업데이트 후 재요청 (여기에 대한 200응답은 어케 체크되는거?)
                var accessToken = ""
                runBlocking {
                    tokenResponse.resultData.accessToken?.let { tokenManager.updateTokenData(it) }
                    accessToken = tokenManager.getTokenData().collect{ it.accessToken }.toString()
                }

                if (accessToken.isEmpty()) {
                    return null
                } else {
                    return response.request
                        .newBuilder()
                        .header(AUTHORIZATION, "Bearer $accessToken").build()
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
