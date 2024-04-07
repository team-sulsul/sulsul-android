plugins {
    id("sulsul.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.teamsulsul.sulsul"

    defaultConfig {
        targetSdk = 34
        applicationId = "com.teamsulsul.sulsul"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.feature.login)
    implementation(projects.core.designsystem)

    implementation(libs.timber)
    implementation(libs.kakao)
}
