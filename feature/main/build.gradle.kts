@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("sulsul.android.feature")
}

android {
    namespace = "com.sulsul.feature.main"
}

dependencies {
    implementation(projects.feature.calendar)
    implementation(projects.feature.report)
    implementation(projects.feature.setting)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}
