package com.sulsul.feature.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.TokenManager
import com.sulsul.core.data.remote.repository.LoginRepository
import com.sulsul.feature.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _loginInfo  = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginInfo: StateFlow<LoginState> = _loginInfo

    private val _errorMsg = MutableStateFlow<String>("")
    val errorMsg: StateFlow<String> = _errorMsg

    // TODO : 서버 측에서 응답 구조, 에러코드 상세화 변경되면 코드 변경 필요
    fun tryLogin(kakaoAccess: String) {
        viewModelScope.launch {
            loginRepository.postLogin(kakaoAccess)
                .catch {e ->
                    _loginInfo.value = LoginState.Failure(e)
                    Timber.d("!!error : $e")
                }.collect {
                    if (it.resultCode.toInt() == 200) {
                        _loginInfo.value = LoginState.Success(it.resultData)
                        tokenManager.updateTokenData(it.resultData.accessToken)
                        Timber.d("!!success : ${it.resultMessage}")
                    } else {
                        _loginInfo.value = LoginState.Loading(it.resultData)
                        Timber.d(it.resultMessage)
                        Timber.d("!!failure : ${it.resultMessage}")
                    }
                }
        }
    }
}
