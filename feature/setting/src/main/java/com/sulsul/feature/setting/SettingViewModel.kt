package com.sulsul.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.remote.model.response.UserInfoResponse
import com.sulsul.core.data.remote.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository
)  : ViewModel() {

    private val _userInfo = MutableStateFlow<UserInfoResponse>(UserInfoResponse(nickname = "", drink = 0))
    val userInfo: StateFlow<UserInfoResponse> = _userInfo

    fun initialize(id: String) {
        getUserInfo(id)
    }

    fun getUserInfo(id: String) {
        viewModelScope.launch {
            settingRepository.getUserInfo(id).collect { data ->
                _userInfo.value = data
            }
        }
    }

    fun postDeleteAccount(id: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            settingRepository.postDeleteAccount(id).collect {
                onSuccess()
            }
        }
    }
}
