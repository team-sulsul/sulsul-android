package com.sulsul.feature.login

import com.sulsul.core.data.remote.model.response.TokenResponse

object TokenState {
    val LOADING = "000"
    val TOKEN_VALID = "200"
    val TOKEN_ACCESS_EXPIRED = "600"
    val TOKEN_REFRESH_EXPIRED = "601"
    val FAILURE = "400"

//    object Loading: TokenState()
//    data class TokenSuccess(val tokenResponse: TokenResponse) : TokenState()
//    data class AccessTokenExpired(val tokenResponse: TokenResponse) : TokenState()
//    data class RefreshTokenExpired(val tokenResponse: TokenResponse) : TokenState()
//    data class Failure(val msg: Throwable): TokenState()
}
