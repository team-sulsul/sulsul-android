@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("sulsul.android.library")
    id("sulsul.android.hilt")
    id("sulsul.kotlin.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.teamsulsul.core.data"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.database)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    testImplementation(libs.turbine)

    implementation(libs.timber)
}
