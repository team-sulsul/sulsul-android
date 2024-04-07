package com.sulsul.feature.login

import android.content.Intent
import android.os.Bundle
import com.sulsul.core.common.base.BaseActivity
import com.sulsul.feature.login.databinding.ActivityLoginBinding
import com.sulsul.feature.main.MainActivity

class LoginActivity : BaseActivity<ActivityLoginBinding> (ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moveToCalendar()
    }

    private fun moveToCalendar() {
        binding.ivLoginKakao.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
