package com.sulsul.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.sulsul.core.common.base.BaseActivity
import com.sulsul.feature.login.databinding.ActivitySplashBinding
import com.sulsul.feature.login.viewModel.LoginViewModel
import com.sulsul.feature.login.viewModel.SplashViewModel
import com.sulsul.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private val TAG = "SplashActivity"
    private val splashViewModel: SplashViewModel by viewModels()

    private var isReady = false
    private var isLoginAvailable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { true }
        initSplashScreen()
    }

    // 스플래시 초기화
    private fun initSplashScreen() {
        checkSulSulToken()

        val content: View = findViewById(android.R.id.content)
        // SplashScreen이 생성되고 그려질 때 계속해서 호출된다.
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isReady) {
                        content.viewTreeObserver.removeOnPreDrawListener(this) // splash screen 제거
                        if (isLoginAvailable) {
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        } else {
                            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        }
                        finish()
                        true
                    } else {
                        Log.d(TAG, "content is not ready")
                        false
                    }
                }
            }
        )
    }

    // 선행될 작업이 있는 경우, 여기서 처리 후 isReady true 처리
    private fun checkSulSulToken() {
        // 토큰 저장되어있으면 토큰 유효성 검사 필요, 토큰 저장이 없으면 isReady = true에 로그인화면으로 이동
        // todo : dataStoreModule 작업 후 실제 토큰으로 수정
        splashViewModel.checkToken("accessToken", "refreshToken")
        autoLogin()
    }

    private fun autoLogin() {
        lifecycleScope.launch {
            splashViewModel.loginState.collect{ loginState ->
                when(loginState) {
                    TokenState.TOKEN_VALID -> {
                        isLoginAvailable = true
                    }
                    TokenState.TOKEN_ACCESS_EXPIRED -> {
                        isLoginAvailable = true
                    }
                    TokenState.TOKEN_REFRESH_EXPIRED -> {
                        isLoginAvailable = false
                    }
                    else -> {
                        isLoginAvailable = false
                    }
                }
                isReady = true
            }
        }
    }
}
