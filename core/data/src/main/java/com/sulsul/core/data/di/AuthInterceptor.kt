package com.sulsul.core.data.di

import com.sulsul.core.data.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if(chain.request().headers["Auth"] == "false"){ // Authorization 헤더가 필요하지 않음
            val request = chain.request().newBuilder().build()
            return chain.proceed(request)
        }

        val accessToken = runBlocking {
            tokenManager.getTokenData().collect{
                it.accessToken
            }
        }

        val request = chain.request().newBuilder().header(AUTHORIZATION, "Bearer $accessToken").build()
        return chain.proceed(request)
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
    }
}

