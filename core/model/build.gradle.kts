@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("sulsul.android.library")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.teamsulsul.core.model"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
