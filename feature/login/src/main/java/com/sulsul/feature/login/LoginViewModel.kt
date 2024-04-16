package com.sulsul.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.model.remote.request.LoginRequest
import com.sulsul.core.domain.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    fun postLogin(kakaoAccess: LoginRequest) {
        viewModelScope.launch {
            val response = loginUseCase.invoke(kakaoAccess)
        }
    }
}
