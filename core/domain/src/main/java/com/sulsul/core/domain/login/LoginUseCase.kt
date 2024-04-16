package com.sulsul.core.domain.login

import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.core.data.remote.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(kakaoAccess: LoginRequest) = loginRepository.postLogin(kakaoAccess)
}
