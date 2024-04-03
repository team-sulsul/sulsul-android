import com.teamsulsul.sulsul.configureHiltAndroid
import com.teamsulsul.sulsul.libs

plugins {
    id("sulsul.android.library")
    id("sulsul.android.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }
}

configureHiltAndroid()

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))

    val libs = project.extensions.libs
    implementation(libs.findLibrary("hilt.navigation.compose").get())
    implementation(libs.findLibrary("androidx.compose.navigation").get())
    androidTestImplementation(libs.findLibrary("androidx.compose.navigation.test").get())

    implementation(libs.findLibrary("androidx.lifecycle.viewModel").get())
    implementation(libs.findLibrary("androidx.lifecycle.runtime").get())

    implementation(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
    implementation(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())

    implementation(libs.findLibrary("androidx.navigation.fragment").get())
    implementation(libs.findLibrary("androidx.navigation.ui").get())

    implementation(libs.findLibrary("material").get())
}
