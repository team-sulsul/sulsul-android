package com.sulsul.feature.login

import com.sulsul.core.data.remote.model.response.TokenResult

sealed class TokenState {
    object Initial : TokenState()
    class Loading(val data: TokenResult) : TokenState()
    class Failure(val msg: Throwable) : TokenState()
    class Success(val data: TokenResult) : TokenState()
}
