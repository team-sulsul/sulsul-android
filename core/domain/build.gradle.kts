@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("sulsul.android.library")
}

android {
    namespace = "com.sulsul.core.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
}