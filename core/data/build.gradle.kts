@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("sulsul.android.library")
    id("sulsul.android.hilt")
    id("sulsul.kotlin.hilt")
}

android {
    namespace = "com.teamsulsul.core.data"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.database)
}
