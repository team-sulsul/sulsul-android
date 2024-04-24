package com.sulsul.feature.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.domain.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val success = "200"
    private val TAG = "login"

    private val _loinSuccess = MutableStateFlow<Boolean>(false)
    val loginSuccess: StateFlow<Boolean> = _loinSuccess

    private val _errorMsg = MutableStateFlow<String>("")
    val errorMsg: StateFlow<String> = _errorMsg

    // TODO : 서버 측에서 응답 구조, 에러코드 상세화 변경되면 코드 변경 필요
    fun postLogin(kakaoAccess: String) {
        viewModelScope.launch {
            loginUseCase.tryLogin(kakaoAccess)
                .catch {
                    _errorMsg.value = "failed"
                    Timber.tag(TAG).d(it)
                }.collect {
                    if (it.message == success) {
                        _loinSuccess.value = true
                    } else {
                        _errorMsg.value = "failed" // 서버에서 fail에 대한 응답을 줘야 수정할 수 있을듯..?
                        Timber.tag(TAG).d(it.message)
                    }
                }
        }
    }
}
