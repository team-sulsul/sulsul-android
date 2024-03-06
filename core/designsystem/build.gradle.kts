@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("sulsul.android.library")
    id("sulsul.android.compose")
}

android {
    namespace = "com.sulsul.core.designsystem"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.material)
}
