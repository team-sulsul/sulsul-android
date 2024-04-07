plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "sulsul.android.hilt"
            implementationClass = "com.teamsulsul.sulsul.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "sulsul.kotlin.hilt"
            implementationClass = "com.teamsulsul.sulsul.HiltKotlinPlugin"
        }
        register("androidRoom") {
            id = "sulsul.android.room"
            implementationClass = "com.teamsulsul.sulsul.AndroidRoomPlugin"
        }
    }
}
