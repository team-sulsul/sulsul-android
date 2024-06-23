package com.teamsulsul.sulsul

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SulSulApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        // TODO : 네이티브 앱 키 local.properties 사용하여 보호하기
        KakaoSdk.init(this, getString(R.string.kakao_native_app_key))
//        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}
