import com.foundation.widget.buildsrc.Dependencies
import com.foundation.widget.buildsrc.Publish

plugins {
    id("com.android.library")
    id("kotlin-android")
}
apply("common.gradle")
android {
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
//            consumerProguardFiles("consumer-rules.pro") lib单独打包 此属性是无效的
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildTypes.forEach {
        it.buildConfigField("Integer", "versionCode", Publish.Version.versionCode.toString())
        it.buildConfigField("String", "versionName", "\"${Publish.Version.versionName}\"")
        it.buildConfigField("String", "versionTimeStamp", "\"${Publish.Version.versionTimeStamp}\"")
    }
    sourceSets {
        getByName("main") {
            java {
                srcDirs("src/main/java")
            }
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(Dependencies.Kotlin.kotlin_stdlib)
    implementation(Dependencies.AndroidX.core_ktx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Material.material)
    implementation(Dependencies.AndroidX.constraintlayout)
}