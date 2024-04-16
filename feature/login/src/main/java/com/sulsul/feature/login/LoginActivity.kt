package com.sulsul.feature.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.sulsul.core.common.base.BaseActivity
import com.sulsul.core.data.remote.model.request.LoginRequest
import com.sulsul.feature.login.databinding.ActivityLoginBinding
import com.sulsul.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding> (ActivityLoginBinding::inflate) {

    private val TAG = "LoginActivity"
    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var callback: (OAuthToken?, Throwable?) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callback()
        moveToCalendar()
    }

    fun callback() {
        callback = { token, error ->
            if (error != null) {
                Timber.tag(TAG).d("[kakao login] error callback : ${error.message}")
            } else if (token != null) {
                // 로그인 성공
                Timber.tag(TAG).d("[kakao login] login success")
                Timber.tag(TAG).d("[kakao login] kakao access token : ${token.accessToken}, ${token.accessTokenExpiresAt}")
                Timber.tag(TAG).d("[kakao login] kakao refresh token : ${token.refreshToken}, ${token.refreshTokenExpiresAt}")

                // 인가코드 post
                val access_token = token.accessToken
                val loginRequest = LoginRequest(access_token)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                loginViewModel.postLogin(loginRequest)
            }
        }
    }

    private fun moveToCalendar() {
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
                        Toast.makeText(this, "카카오톡으로 로그인 성공!", Toast.LENGTH_SHORT).show()
                        Timber.tag(TAG).d("카카오톡으로 로그인 성공! ${token.accessToken}")

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    // kakao로 로그인 하지 못 할 경우 계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }
}
