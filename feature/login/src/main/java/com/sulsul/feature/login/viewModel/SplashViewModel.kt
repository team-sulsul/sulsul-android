package com.sulsul.feature.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.remote.repository.LoginRepository
import com.sulsul.feature.login.TokenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<String>(TokenState.LOADING)
    val loginState: StateFlow<String> = _loginState

    val tokenData = loginRepository.getTokenData()

    // 결과에 따라 이동활 화면 구분
    fun checkToken(accessToken: String, refreshToken: String) {
        viewModelScope.launch {
            loginRepository.postToken(accessToken, refreshToken)
                .catch {

                }.collect {
                    if (it.resultCode.toInt() == 200) {
                        when (it.resultData.message) {
                            TokenState.TOKEN_VALID -> { // 토큰 정상
                                _loginState.value = TokenState.TOKEN_VALID
                            }

                            TokenState.TOKEN_ACCESS_EXPIRED -> { // accessToken 만료
                                tokenData.collect{tokenData ->
                                    val accessToken = it.resultData.accessToken
                                    val refreshToken = tokenData.refreshToken
                                    if (accessToken != null) {
                                        loginRepository.updateTokenData(accessToken, refreshToken)
                                    }
                                    _loginState.value = TokenState.TOKEN_ACCESS_EXPIRED
                                }
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
}
