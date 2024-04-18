package com.sulsul.feature.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.sulsul.core.common.base.BaseActivity
import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.feature.login.databinding.ActivityLoginBinding
import com.sulsul.feature.login.viewModel.LoginViewModel
import com.sulsul.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val TAG = "Login"
    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var callback: (OAuthToken?, Throwable?) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callback()
        kakaoLogin()
    }

    private fun callback() {
        callback = { token, error ->
            if (error != null) {
                Timber.tag(TAG).d("[kakao login] error callback : ${error.message}")
            } else if (token != null) {
                sulsulLogin(token)
            }
        }
    }

    private fun kakaoLogin() {
        binding.ivLoginKakao.setOnClickListener {
            // kakao 실행 가능 여부
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Toast.makeText(this, "카카오톡 로그인 실패", Toast.LENGTH_SHORT).show()
                        Timber.tag(TAG).d("카카오톡 로그인 실패 ${error.message}")

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                    } else if (token != null) {
                        sulsulLogin(token)
                    }

                    // kakao로 로그인 하지 못 할 경우 계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    private fun sulsulLogin(token: OAuthToken) {
        // 카카오 로그인 성공
        Timber.tag(TAG).d("[kakao login] login success")
        Timber.tag(TAG).d("[kakao login] kakao access token : ${token.accessToken}, ${token.accessTokenExpiresAt}")
        Timber.tag(TAG).d("[kakao login] kakao refresh token : ${token.refreshToken}, ${token.refreshTokenExpiresAt}")

        // 술술 서버 로그인 시도
        val access_token = token.accessToken
        loginViewModel.postLogin(access_token)
        moveToMain()
    }

    fun moveToMain() {
        lifecycleScope.launch {
            loginViewModel.loginSuccess.collect { success ->
                if (success) {
                    Timber.tag(TAG).d("[sulsul login] sulsul login success")
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else if (loginViewModel.errorMsg.isNotEmpty()) { // 서버 응답 바뀌면 변경되어야 함
                    Timber.tag(TAG).d("[sulsul login] sulsul login failed ${loginViewModel.errorMsg}")
                    finish()
                }
            }
        }
    }
}
