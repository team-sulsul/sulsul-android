package com.sulsul.core.domain.login

import com.sulsul.core.data.model.remote.request.LoginRequest
import com.sulsul.core.data.repository.remote.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(authCode: LoginRequest) = loginRepository.postLogin(authCode)
}

