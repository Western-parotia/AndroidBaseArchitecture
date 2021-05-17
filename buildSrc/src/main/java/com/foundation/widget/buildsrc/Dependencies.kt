package com.foundation.widget.buildsrc

/**
 *@Desc:
 *-
 *-依赖声明
 *create by zhusw on 5/6/21 15:45
 */
object Dependencies {
    object Kotlin {
        /**
         * kotlin 语言核心库，像 let这种操作域拓展
         */
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:1.4.32"
    }

    object AndroidX {
        /**
         * kotlin 标准库，各种推展方法，像 foreach什么的
         */
        const val core_ktx = "androidx.core:core-ktx:1.3.2"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

    }

    object Material {
        const val material = "com.google.android.material:material:1.3.0"
    }

    /**
     * ktx 库 版本：https://developer.android.google.cn/kotlin/ktx?hl=zh-cn
     */
    object Coroutines {
        const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
        const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        const val lifecycle_liveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"
        const val lifecycle_viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"

    }

}
