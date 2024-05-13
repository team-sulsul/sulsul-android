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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    object TokenValidState { // todo : 이걸 어따 두지
        val LOADING = "000"
        val TOKEN_VALID = "200"
        val TOKEN_ACCESS_EXPIRED = "400"
        val TOKEN_REFRESH_EXPIRED = "401"
        val FAILURE = "400"
    }

    private val _tokenInfo = MutableStateFlow<TokenState>(TokenState.Initial)
    val tokenInfo: StateFlow<TokenState> = _tokenInfo

    private val _loginState = MutableStateFlow<String>(TokenValidState.LOADING)
    val loginState: StateFlow<String> = _loginState

    val tokenData = loginRepository.getTokenData()

    // 결과에 따라 이동활 화면 구분
    fun checkToken(accessToken: String) {
        viewModelScope.launch {
            loginRepository.postToken(accessToken)
                .catch {e ->
                    _tokenInfo.value = TokenState.Failure(e)
                    Timber.d("!!error : $e")
                }.collect {
                    if (it.resultCode.toInt() == 200) {
                        _tokenInfo.value = TokenState.Success(it.resultData)
                        Timber.d("!!success : ${it.resultData.message}")

                        when (it.resultData.message) {
                            TokenValidState.TOKEN_VALID -> { // 토큰 정상
                                _loginState.value = TokenValidState.TOKEN_VALID
                            }

                            TokenValidState.TOKEN_ACCESS_EXPIRED -> { // accessToken 만료
                                tokenData.collect{tokenData ->
                                    val accessToken = it.resultData.accessToken
                                    if (accessToken != null) {
                                        loginRepository.updateTokenData(accessToken)
                                    }
                                    _loginState.value = TokenValidState.TOKEN_ACCESS_EXPIRED
                                }
                            }

                            TokenValidState.TOKEN_REFRESH_EXPIRED -> { // refreshToken 만료
                                _loginState.value = TokenValidState.TOKEN_REFRESH_EXPIRED
                            }

                            else -> {
                                _loginState.value = TokenValidState.FAILURE
                            }
                        }
                    } else {
                        _tokenInfo.value = TokenState.Loading(it.resultData)
                        Timber.d("!!failure : ${it.resultMessage}")
                    }
                }
        }
    }
}
