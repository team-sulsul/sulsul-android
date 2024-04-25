package com.sulsul.core.domain.login

import com.sulsul.core.data.remote.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend fun tryLogin(kakaoAccess: String) = loginRepository.postLogin(kakaoAccess = kakaoAccess)
}
