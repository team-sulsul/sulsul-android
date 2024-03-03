pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "sulsul-android"
include(
    ":app",

    ":core",
    ":core:model",
    ":core:database",
    ":core:data",
    ":core:datastore",
    ":core:domain",
    ":core:designsystem",
    ":core:common",

    ":feature",
    ":feature:main"
)