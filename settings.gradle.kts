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
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/") }
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
    ":feature:login",
    ":feature:main",
    ":feature:calendar",
    ":feature:report",
    ":feature:setting"
)
