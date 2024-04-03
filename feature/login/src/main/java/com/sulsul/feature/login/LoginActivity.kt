package com.sulsul.feature.login

import android.os.Bundle
import androidx.activity.viewModels
import com.sulsul.core.common.base.BaseActivity
import com.sulsul.core.data.model.remote.request.LoginRequest
import com.sulsul.feature.login.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding> (ActivityLoginBinding::inflate) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moveToCalendar()
    }

    private fun moveToCalendar() {
        binding.ivLoginKakao.setOnClickListener {
              loginViewModel.postLogin(LoginRequest("authcode"))
        }
    }
}
