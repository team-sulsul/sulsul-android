//import java.util.Properties

plugins {
    id("sulsul.android.application")
    id("org.jetbrains.kotlin.android")
}

//val properties = Properties()
//properties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.teamsulsul.sulsul"

    defaultConfig {
//        buildConfigField(
//            "String",
//            "KAKAO_NATIVE_APP_KEY",
//            properties.getProperty("KAKAO_NATIVE_APP_KEY")
//        )
//        manifestPlaceholders["KAKAO_NATIVE_APP_KEY"] = properties.getProperty("KAKAO_NATIVE_APP_KEY")

        targetSdk = 34
        applicationId = "com.teamsulsul.sulsul"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

//    buildFeatures {
//        buildConfig = true
//    }

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
