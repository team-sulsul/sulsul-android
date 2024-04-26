package com.sulsul.feature.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.domain.login.LoginUseCase
import com.sulsul.feature.login.TokenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<String>(TokenState.LOADING)
    val loginState: StateFlow<String> = _loginState

    // 결과에 따라 이동활 화면 구분
    fun checkToken(accessToken: String, refreshToken: String) {
        viewModelScope.launch {
            loginUseCase.checkToken(accessToken, refreshToken)
                .catch {

                }.collect {
                    when (it.message) {
                        TokenState.TOKEN_VALID -> { // 토큰 정상
                            _loginState.value = TokenState.TOKEN_VALID
                        }
                        TokenState.TOKEN_ACCESS_EXPIRED -> { // accessToken 만료
                            _loginState.value = TokenState.TOKEN_ACCESS_EXPIRED
                        }
                        TokenState.TOKEN_REFRESH_EXPIRED -> { // refreshToken 만료
                            _loginState.value = TokenState.TOKEN_REFRESH_EXPIRED
                        }
                        else -> {
                            _loginState.value = TokenState.FAILURE
                        }
                    }
                }
        }
    }
}
