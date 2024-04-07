package com.sulsul.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sulsul.core.common.base.BaseActivity
import com.sulsul.feature.login.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private val TAG = "SplashActivity"
    private var isReady = false
    private val DURATION: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { true }
        initSplashScreen()
    }

    // 스플래시 초기화
    private fun initSplashScreen() {
        initData()

        val content: View = findViewById(android.R.id.content)
        // SplashScreen이 생성되고 그려질 때 계속해서 호출된다.
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isReady) {
                        content.viewTreeObserver.removeOnPreDrawListener(this) // splash screen 제거
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
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
    private fun initData() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(DURATION)
            isReady = true
        }
    }
}
