import com.foundation.widget.buildsrc.Dependencies
plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.foundation.app.simple"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        create("normalSign") {
            storeFile = file("test.jks")
            storePassword = "android"
            keyAlias = "android"
            keyPassword = "android"
        }
    }
    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("normalSign")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("normalSign")
            isMinifyEnabled = true
        }

    }
    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

configurations.all {
    resolutionStrategy {
        //版本号会改变
        cacheDynamicVersionsFor(10, TimeUnit.SECONDS)
        //版本号不变,1.0-SNAPSHOT 这种应该实时更新
        cacheChangingModulesFor(10, TimeUnit.SECONDS)

    }
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(Dependencies.Kotlin.kotlin_stdlib)
    implementation(Dependencies.Ktx.core_ktx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Material.material)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.Coroutines.coroutines_android)
    implementation(Dependencies.Ktx.lifecycle_liveData_ktx)
    implementation(Dependencies.Ktx.lifecycle_runtime_ktx)
    implementation(Dependencies.Ktx.lifecycle_viewModel_ktx)
    implementation(Dependencies.Ktx.fragment_ktx)
    implementation("com.foundation.widget:loading:1.0-SNAPSHOT")
    implementation(project(":baseAF"))

}
repositories {
    maven {
        setUrl(com.foundation.widget.buildsrc.Publish.Maven.codingArtifactsRepoUrl)
        credentials {
            username = com.foundation.widget.buildsrc.Publish.Maven.codingArtifactsGradleUsername
            password = com.foundation.widget.buildsrc.Publish.Maven.codingArtifactsGradlePassword
        }
    }
}