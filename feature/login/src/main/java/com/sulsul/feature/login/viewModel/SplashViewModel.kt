package com.sulsul.feature.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.TokenManager
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
    private val loginRepository: LoginRepository,
    tokenManager: TokenManager
) : ViewModel() {

    object TokenValidState { // todo : 이걸 어따 두지
        val LOADING = "000"
        val TOKEN_VALID = "200"
    }

    private val _tokenInfo = MutableStateFlow<TokenState>(TokenState.Initial)
    val tokenInfo: StateFlow<TokenState> = _tokenInfo

    private val _loginState = MutableStateFlow<String>(TokenValidState.LOADING)
    val loginState: StateFlow<String> = _loginState

    val tokenData = tokenManager.getTokenData()

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
                        Timber.d("!!success : ${it.resultMessage}")
                        _loginState.value = TokenValidState.TOKEN_VALID
                    } else {
                        _tokenInfo.value = TokenState.Loading(it.resultData)
                        Timber.d("!!failure : ${it.resultMessage}")
                    }
                }
        }
    }
}
