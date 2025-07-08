import java.net.URI

pluginManagement {
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
        maven { url = URI("https://jitpack.io") }
//        maven {  "https://jitpack.io" }
//        maven( "https://jitpack.io")
//        maven { url "https://jitpack.io" }
//        maven{
//            uri("https://jitpack.io" )
//        }

    }
}

rootProject.name = "Kabreet"
include(":app")
