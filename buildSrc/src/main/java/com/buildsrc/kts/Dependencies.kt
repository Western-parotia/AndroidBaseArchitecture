package com.buildsrc.kts

import org.gradle.api.JavaVersion

object Dependencies {
    const val kotlinVersion = "1.7.0"
    const val agp = "7.4.2"

    val javaVersion = JavaVersion.VERSION_1_8
    val jvmTarget = "11"

    object Kotlin {
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    }

    object Foundation {
        const val loading = "com.foundation.widget:loadingview:1.1.9"
        const val viewBindingHelper = "com.foundation.widget:view-binding-helper:1.0.6"
        const val net = "com.foundation.service:net:1.0.5"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

    }

    object Material {
        const val material = "com.google.android.material:material:1.3.0"
    }

    object Coroutines {
        const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"

    }

    object Retrofit {
        const val retorifit = "com.squareup.retrofit2:retrofit:2.9.0"//依赖okhttp 3.14.9
        const val converter_gson = "com.squareup.retrofit2:converter-gson:2.9.0"
        const val url_manager = "me.jessyan:retrofit-url-manager:1.4.0"
        const val gson = "com.google.code.gson:gson:2.8.5"
    }

    object UI {
        const val BaseRecyclerViewAdapterHelper =
            "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4"
        const val glde =
            "com.github.bumptech.glide:glide:4.3.1"
    }

    /**
     * ktx 库清单与版本：https://developer.android.google.cn/kotlin/ktx?hl=zh-cn
     */
    object JetPack {
        const val core_ktx = "androidx.core:core-ktx:1.3.2"
        const val fragment_ktx = "androidx.fragment:fragment-ktx:1.3.3"

        /*Lifecycle 拓展协程*/
        const val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"

        /*livedata 拓展协程*/
        const val lifecycle_liveData_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"

        //        "androidx.lifecycle:lifecycle-livedata-core:2.3.1"
        /*viewModel 拓展协程*/
        const val lifecycle_viewModel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"


    }
}