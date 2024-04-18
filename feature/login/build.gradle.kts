//import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("sulsul.android.feature")
}

//val properties = Properties()
//properties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.sulsul.feature.login"

//    defaultConfig {
//        manifestPlaceholders["KAKAO_SCHEME_NATIVE_APP_KEY"] = properties.getProperty("KAKAO_SCHEME_NATIVE_APP_KEY")
//    }
}

dependencies {
    implementation(projects.feature.main)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.splash.screen)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    implementation(libs.timber)
    implementation(libs.kakao)
}
