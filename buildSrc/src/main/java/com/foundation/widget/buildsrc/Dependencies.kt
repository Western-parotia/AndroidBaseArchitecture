package com.foundation.widget.buildsrc

/**

 *
 * 依赖声明
 *create by zhusw on 5/6/21 15:45
 */
object Dependencies {
    object Dex {
        const val multidex = "androidx.multidex:multidex:2.0.0"
    }

    object Foundation {
        const val loading = "com.foundation.widget:loading:1.0-SNAPSHOT"
    }

    object Kotlin {
        /**
         * kotlin 语言核心库，像 let这种操作域拓展
         */
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:1.4.32"
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

    /**
     * ktx 库清单与版本：https://developer.android.google.cn/kotlin/ktx?hl=zh-cn
     */
    object Ktx {
        const val core_ktx = "androidx.core:core-ktx:1.3.2"
        const val fragment_ktx = "androidx.fragment:fragment-ktx:1.3.3"

        /*Lifecycle 拓展协程*/
        const val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"

        /*livedata 拓展协程*/
        const val lifecycle_liveData_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"

        /*viewModel 拓展协程*/
        const val lifecycle_viewModel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
    }

}
