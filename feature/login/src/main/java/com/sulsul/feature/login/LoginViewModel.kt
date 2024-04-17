package com.sulsul.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.core.domain.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val success = "200"

    private val _loinSuccess = MutableStateFlow<Boolean>(false)
    val loginSuccess: StateFlow<Boolean> = _loinSuccess

    fun postLogin(kakaoAccess: LoginRequest) {
        viewModelScope.launch {
            loginUseCase.tryLogin(kakaoAccess)
                .catch {
                }.collect {
                    if (it.message == success) {
                        _loinSuccess.value = true
                    }
                }
        }
    }
}
