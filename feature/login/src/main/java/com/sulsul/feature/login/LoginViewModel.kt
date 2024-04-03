package com.sulsul.feature.login

import android.util.Log
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
): ViewModel() {
    fun postLogin(authCode: LoginRequest) {
        viewModelScope.launch {
            val response = loginUseCase.invoke(authCode)
            // Log.d("[check response]", response.toString())
        }
    }

//    fun getChatList() {
//        viewModelScope.launch {
//            _chatListInfo.value = ChatState.Loading
//            getChatListUseCase.invoke().catch { e ->
//                Timber.d("!!에러 $e")
//                _chatListInfo.value = ChatState.Failure(e)
//            }.collect {
//                Timber.d("!!뷰모델 $it")
//                it?.let {
//                    _chatListInfo.value = ChatState.Success(it)
//                }
//            }
//        }
//    }
}
