package com.sulsul.feature.login.viewModel

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
    var errorMsg = ""

    private val _loinSuccess = MutableStateFlow<Boolean>(false)
    val loginSuccess: StateFlow<Boolean> = _loinSuccess

    // TODO : 서버 측에서 응답 구조, 에러코드 상세화 변경되면 코드 변경 필요
    fun postLogin(kakaoAccess: String) {
        viewModelScope.launch {
            loginUseCase.tryLogin(kakaoAccess)
                .catch {
                }.collect {
                    if (it.message == success) {
                        _loinSuccess.value = true
                    } else {
                        _loinSuccess.value = false
                        errorMsg = "failed" // 서버에서 fail에 대한 응답을 줘야 수정할 수 있을듯..?
                    }
                }
        }
    }
}
