package com.sulsul.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.TokenManager
import com.sulsul.core.data.remote.model.response.UserInfoResponse
import com.sulsul.core.data.remote.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _userInfo = MutableStateFlow<UserInfoResponse>(UserInfoResponse(nickname = "", totalBottle = 0, totalDrink = 0))
    val userInfo: StateFlow<UserInfoResponse> = _userInfo

    fun initialize() {
        getUserInfo()
    }

    fun getUserInfo() {
        viewModelScope.launch {
            settingRepository.getUserInfo().collect { data ->
                _userInfo.value = data
            }
        }
    }

    fun postDeleteAccount(onSuccess: () -> Unit) {
        viewModelScope.launch {
            settingRepository.postDeleteAccount().collect {
                onSuccess()
            }
        }
    }

    fun deleteToken() {
        viewModelScope.launch {
            tokenManager.clearTokenData()
        }
    }
}
