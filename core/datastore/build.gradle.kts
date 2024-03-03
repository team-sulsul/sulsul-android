@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("sulsul.android.library")
}

android {
    namespace = "com.sulsul.core.datastore"
}

dependencies {
    implementation(libs.androidx.datastore)
}