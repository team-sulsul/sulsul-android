package com.sulsul.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sulsul.core.common.base.BaseActivity
import com.sulsul.feature.login.databinding.ActivitySplashBinding
 import com.sulsul.feature.login.viewModel.SplashViewModel
import com.sulsul.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

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
                            Timber.d("[sulsul login] sulsul autoLogin success.")
                            Toast.makeText(this@SplashActivity, "술술 로그인 성공", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        } else {
                            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        }
                        finish()
                        true
                    } else {
                        Timber.d("content is not ready")
                        false
                    }
                }
            }
        )
    }

    // 선행될 작업이 있는 경우, 여기서 처리 후 isReady true 처리
    private fun checkSulSulToken() {
        // 토큰 저장되어있으면 토큰 유효성 검사 필요, 토큰 저장이 없으면 isReady = true에 로그인화면으로 이동
        lifecycleScope.launch {
            splashViewModel.tokenData.collect{tokenData ->
                val accessToken = tokenData.accessToken
                val refreshToken = tokenData.refreshToken
                if (accessToken.isEmpty() || refreshToken.isEmpty()) {
                    Timber.tag("checkToken in dataStore").d("no token!! accessToken : $accessToken, refreshToken : $refreshToken")
                    isReady = true
                } else {
                    Timber.tag("checkToken in dataStore").d("accessToken : $accessToken, refreshToken : $refreshToken")
                    splashViewModel.checkToken(accessToken, refreshToken)
                    observeTokenInfo()
                }
            }
        }
    }

    private fun observeTokenInfo() {
        lifecycleScope.launch {
            splashViewModel.tokenInfo.collect{ state ->
                when (state) {
                    is TokenState.Initial -> {}
                    is TokenState.Loading -> {}
                    is TokenState.Failure -> {}
                    is TokenState.Success -> {
                        observeTokenValidState()
                    }
                }
            }
        }
    }

    private fun observeTokenValidState() {
        lifecycleScope.launch {
         splashViewModel.loginState.collect{ loginState ->
                Timber.tag("loginState").d(loginState)
                when(loginState) {
                    SplashViewModel.TokenValidState.TOKEN_VALID -> {
                        isLoginAvailable = true
                        isReady = true
                    }
                    SplashViewModel.TokenValidState.TOKEN_ACCESS_EXPIRED -> {
                        isLoginAvailable = true
                        isReady = true
                    }
                  SplashViewModel.TokenValidState.TOKEN_REFRESH_EXPIRED -> {
                        isLoginAvailable = false
                        isReady = true
                    }
                    else -> { // Loading
                  }
                }
            }
        }
    }
}
