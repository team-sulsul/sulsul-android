package com.sulsul.feature.login

import com.sulsul.core.data.remote.model.response.LoginResult
sealed class LoginState {
    object Initial : LoginState()
    class Loading(val data: LoginResult) : LoginState()
    class Failure(val msg: Throwable) : LoginState()
    class Success(val data: LoginResult) : LoginState()
}
