package com.sulsul.core.data.di

import com.sulsul.core.data.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Timber.tag("checking Auth").d("authInterceptor")
//        if(chain.request().headers["Auth"] == "false"){ // Authorization 헤더가 필요하지 않음
//            val request = chain.request().newBuilder().build()
//            return chain.proceed(request)
//        }

        val accessToken = runBlocking {
            var token = tokenManager.getTokenData().first()
            Timber.tag("checking auth").d("accessToken : ${token.accessToken}")
            token.accessToken
        }

        Timber.tag("checking auth").d("authInterceptor, add Bearer with accessToken ${accessToken}")
        val request = chain.request().newBuilder().header(AUTHORIZATION, "Bearer ${accessToken}").build()
        return chain.proceed(request)
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
    }
}

