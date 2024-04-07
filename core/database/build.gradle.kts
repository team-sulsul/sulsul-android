@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    kotlin("kapt")
    id("sulsul.android.library")
    id("sulsul.android.hilt")
}

android {
    namespace = "com.teamsulsul.core.database"
}

dependencies {
    implementation(projects.core.model)

    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
}
